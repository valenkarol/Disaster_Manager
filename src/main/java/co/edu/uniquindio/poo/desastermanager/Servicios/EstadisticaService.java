package co.edu.uniquindio.poo.desastermanager.Servicios;
import co.edu.uniquindio.poo.desastermanager.Modelo.*;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.EstadisticaZonaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ColaPrioridad;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EstadisticaService {

    @Autowired
    private ZonaService zonaService; // Devuelve ListaSimpleEnlazada<Zona>

    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private RecursoService recursoService;
    @Autowired
    private EvacuacionService evacuacionService; // Devuelve ListaSimpleEnlazada<Evacuacion>

    private ColaPrioridad<Evacuacion> colaPrioridad = new ColaPrioridad<>();

    /**
     * Genera estadísticas por zona usando tus listas propias.
     */
    public ListaSimpleEnlazada<Estadistica> generarEstadisticas() {

        ListaSimpleEnlazada<Estadistica> estadisticas = new ListaSimpleEnlazada<>();

        ListaSimpleEnlazada<Zona> zonas = zonaService.listarZonas(); // tu lista propia

        for (Zona zona : zonas) {

            int personasEvacuadas = 0;
            int personasPendientes = 0;

            ListaSimpleEnlazada<Evacuacion> evacuaciones = evacuacionService.listarEvacuaciones();

            for (Evacuacion ev : evacuaciones) {

                // Compara por zona
                if (ev.getZona().getId().equals(zona.getId())) {

                    // Si tu modelo Evacuacion no tiene isProcesada, aquí puedes usar otra lógica:
                    // ejemplo: considerar procesadas las evacuaciones que ya fueron sacadas de la cola
                    if (evacuacionService.esProcesada(ev)) { // método que implementes
                        personasEvacuadas += ev.getNumeroAfectados();
                    } else {
                        personasPendientes += ev.getNumeroAfectados();
                    }
                }
            }

            int totalPersonas = personasEvacuadas + personasPendientes;
            int recursosDisponibles = zona.getNivelRiesgo() * 10; // ejemplo

            Estadistica est = new Estadistica(zona, totalPersonas, personasEvacuadas, personasPendientes, recursosDisponibles);
            estadisticas.agregarUltimo(new NodoLS<>(est));
        }

        return estadisticas;
    }

    public boolean esProcesada(Evacuacion ev) {
        // Si está en la cola → no procesada
        return !colaPrioridad.contiene(ev);
    }

    public List<EstadisticaZonaDTO> generarDashboardZonas() {

        List<EstadisticaZonaDTO> resultado = new ArrayList<>();

        ListaSimpleEnlazada<Zona> zonas = zonaService.listarZonas();
        ListaSimpleEnlazada<Evacuacion> evacuaciones = evacuacionService.listarEvacuaciones();
        ListaSimpleEnlazada<Recurso> recursos = recursoService.listarRecursos();

        for (Zona z : zonas) {

            int evacuados = 0;
            int pendientes = 0;

            // Buscar evacuaciones relacionadas a esta zona
            for (Evacuacion ev : evacuaciones) {
                if (ev.getZona() != null && ev.getZona().getId().equals(z.getId())) {
                    if (evacuacionService.esProcesada(ev))
                        evacuados += ev.getNumeroAfectados();
                    else
                        pendientes += ev.getNumeroAfectados();
                }
            }

            // Buscar recursos asignados a esta zona
            int recursosTotales = 0;
            for (Recurso r : recursos) {
                if (z.getId().equals(r.getZonaAsignada())) {
                    recursosTotales += r.getCantidad();
                }
            }

            resultado.add(
                    new EstadisticaZonaDTO(
                            z.getId(),
                            z.getNombreZona(),
                            z.getNivelRiesgo(),
                            evacuados,
                            pendientes,
                            recursosTotales
                    )
            );
        }

        return resultado;
    }


}

package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Estadistica;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstadisticaZonaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Servicios.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticaController {

    @Autowired
    private EstadisticaService estadisticaService;


    @GetMapping
    public ResponseEntity<List<Estadistica>> obtenerEstadisticas() {
        try {
            ListaSimpleEnlazada<Estadistica> listaPropia = estadisticaService.generarEstadisticas();
            List<Estadistica> listaJson = new ArrayList<>();

            NodoLS<Estadistica> nodo = listaPropia.getPrimero();
            while (nodo != null) {
                listaJson.add(nodo.getDato());
                nodo = nodo.getProximo();
            }

            return new ResponseEntity<>(listaJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prioridad/{nivel}")
    public ResponseEntity<List<Estadistica>> filtrarPorPrioridad(@PathVariable String nivel) {
        try {
            ListaSimpleEnlazada<Estadistica> listaPropia = estadisticaService.generarEstadisticas();
            List<Estadistica> listaFiltrada = new ArrayList<>();

            NodoLS<Estadistica> nodo = listaPropia.getPrimero();
            while (nodo != null) {
                Estadistica est = nodo.getDato();
                int nivelRiesgo = est.getZona().getNivelRiesgo();

                // Mapear nivel textual a números según tu convención
                if (("alta".equalsIgnoreCase(nivel) && nivelRiesgo >= 3) ||
                        ("media".equalsIgnoreCase(nivel) && nivelRiesgo == 2) ||
                        ("baja".equalsIgnoreCase(nivel) && nivelRiesgo == 1) ||
                        ("todas".equalsIgnoreCase(nivel))) {
                    listaFiltrada.add(est);
                }

                nodo = nodo.getProximo();
            }

            return new ResponseEntity<>(listaFiltrada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dashboard/zonas")
    public ResponseEntity<List<EstadisticaZonaDTO>> dashboardZonas() {
        try {
            List<EstadisticaZonaDTO> data = estadisticaService.generarDashboardZonas();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


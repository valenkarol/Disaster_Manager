package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.*;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EquipoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.InformeRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RecursoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InformeService {
    @Autowired
    private InformeRepository informeRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    public Informe crearInforme() {
        Informe informe = new Informe();
        informe.setFecha(LocalDateTime.now());
        return informeRepository.save(informe);
    }

    // READ - todos LISTA PROPIA
    public ListaSimpleEnlazada<Informe> listarInformes() {
        ListaSimpleEnlazada<Informe> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Informe> listaMongo = informeRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Informe informe : listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(informe));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }

    // READ - por ID
    public Optional<Informe> obtenerInformePorId(String id) {
        return informeRepository.findById(id);
    }

    public Informe actualizarInforme(String id, Informe informeActualizado) {
        Optional<Informe> informeOptional = informeRepository.findById(id);
        if (informeOptional.isPresent()) {
            Informe informe = informeOptional.get();
            informe.setFecha(informeActualizado.getFecha());
            return informeRepository.save(informe);
        }
        return null;
    }

    public void eliminarInforme(String id) {
        informeRepository.deleteById(id);
    }

    public Informe realizarInforme() {
        Informe informe = new Informe();
        informe.setFecha(LocalDateTime.now());

        // Guardar inicialmente para obtener ID
        informe = informeRepository.save(informe);

        StringBuilder resumen = new StringBuilder();

        // ---------------- ZONAS ----------------
        ListaSimpleEnlazada<Zona> listaZonas = new ListaSimpleEnlazada<>();
        zonaRepository.findAll().forEach(z -> listaZonas.agregarUltimo(new NodoLS<>(z)));

        NodoLS<Zona> nodoZona = listaZonas.getPrimero();
        while (nodoZona != null) {
            Zona zona = nodoZona.getDato();
            resumen.append("Zona: ").append(zona.getNombreZona())
                    .append(" | Nivel Riesgo: ").append(zona.getNivelRiesgo()).append("\n");

            // ---------------- RECURSOS ----------------
            ListaSimpleEnlazada<Recurso> listaRecursos = new ListaSimpleEnlazada<>();
            recursoRepository.findAll().forEach(r -> listaRecursos.agregarUltimo(new NodoLS<>(r)));

            NodoLS<Recurso> nodoRecurso = listaRecursos.getPrimero();
            boolean hayRecursos = false;
            while (nodoRecurso != null) {
                Recurso r = nodoRecurso.getDato();
                if (zona.getId().equals(r.getZonaAsignada())) {
                    if (!hayRecursos) {
                        resumen.append("  Recursos:\n");
                        hayRecursos = true;
                    }
                    resumen.append("    - ").append(r.getNombre())
                            .append(" (").append(r.getCantidad()).append(" ").append(r.getTipo()).append(")\n");
                }
                nodoRecurso = nodoRecurso.getProximo();
            }

            // ---------------- EQUIPOS ----------------
            ListaSimpleEnlazada<Equipo> listaEquipos = new ListaSimpleEnlazada<>();
            equipoRepository.findAll().forEach(e -> listaEquipos.agregarUltimo(new NodoLS<>(e)));

            NodoLS<Equipo> nodoEquipo = listaEquipos.getPrimero();
            boolean hayEquipos = false;
            while (nodoEquipo != null) {
                Equipo e = nodoEquipo.getDato();
                if (zona.getId().equals(e.getZonaAsignada())) {
                    if (!hayEquipos) {
                        resumen.append("  Equipos:\n");
                        hayEquipos = true;
                    }
                    resumen.append("    - ").append(e.getTipo())
                            .append(" (").append(e.getCantidadMiembros()).append(" miembros)\n");
                }
                nodoEquipo = nodoEquipo.getProximo();
            }

            resumen.append("\n");
            nodoZona = nodoZona.getProximo();
        }

        // Guardar resumen en el informe
        informe.setResumen(resumen.toString());
        informeRepository.save(informe);

        return informe;
    }
}

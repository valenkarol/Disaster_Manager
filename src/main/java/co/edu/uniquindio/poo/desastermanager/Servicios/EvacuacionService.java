package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ColaPrioridad;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EvacuacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvacuacionService {
    @Autowired
    private EvacuacionRepository evacuacionRepository;

    private ColaPrioridad<Evacuacion> colaPrioridad = new ColaPrioridad<>();

    public Evacuacion crearEvacuacion(Evacuacion nuevaEvacuacion) {
        Evacuacion ev = evacuacionRepository.save(nuevaEvacuacion);

        // Insertar en la cola de prioridad PROPIA
        colaPrioridad.insertar(ev);

        return ev;
    }

    public Evacuacion procesarEvacuacion() {
        return colaPrioridad.extraer(); // Saca la más prioritaria
    }

    public Evacuacion verEvacuacionPrioritaria() {
        return colaPrioridad.primero();
    }

    // READ - todos
    public ListaSimpleEnlazada<Evacuacion> listarEvacuaciones() {
        ListaSimpleEnlazada<Evacuacion> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Evacuacion> listaMongo = evacuacionRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Evacuacion evacuacion: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(evacuacion));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }

    // READ - por ID
    public Optional<Evacuacion> obtenerEvacuacionPorId(String id) {
        return evacuacionRepository.findById(id);
    }

    // UPDATE
    public Evacuacion actualizarEvacuacion(String id, Evacuacion evacuacionActualizada) {
        Optional<Evacuacion> evacuacionOptional = evacuacionRepository.findById(id);
        if (evacuacionOptional.isPresent()) {
            Evacuacion evacuacion = evacuacionOptional.get();
            evacuacion.setNumeroAfectados(evacuacionActualizada.getNumeroAfectados());
            evacuacion.setPrioridad(evacuacionActualizada.getPrioridad());
            return evacuacionRepository.save(evacuacion);
        }
        return null;
    }

    // DELETE
    public void eliminarEvacuacion(String id) {
        evacuacionRepository.deleteById(id);
    }
}

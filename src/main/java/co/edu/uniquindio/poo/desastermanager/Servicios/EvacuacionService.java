package co.edu.uniquindio.poo.desastermanager.Servicios;

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

    public Evacuacion crearEvacuacion(Evacuacion evacuacion) {
        return evacuacionRepository.save(evacuacion);
    }

    // READ - todos CAMBIAR A LISTA PROPIA
    public List<Evacuacion> listarEvacuaciones() {
        return evacuacionRepository.findAll();
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

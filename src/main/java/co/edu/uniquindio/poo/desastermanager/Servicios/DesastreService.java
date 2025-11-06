package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Desastre;
import co.edu.uniquindio.poo.desastermanager.Repositorio.DesastreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesastreService {
    @Autowired
    private DesastreRepository desastreRepository;
    public Desastre crearDesastre(Desastre desastre) {
        return desastreRepository.save(desastre);
    }

    // READ - todos CAMPIAR A LISTA PROPIA
    public List<Desastre> listarDesastres() {
        return desastreRepository.findAll();
    }

    // READ - por ID
    public Optional<Desastre> obtenerDesastrePorId(String id) {
        return desastreRepository.findById(id);
    }
    public Desastre actualizarDesastre(String id, Desastre desastreActualizado) {
        Optional<Desastre> desastreOptional = desastreRepository.findById(id);
        if (desastreOptional.isPresent()) {
            Desastre desastre = desastreOptional.get();
            desastre.setFecha(desastreActualizado.getFecha());
            desastre.setUbicacion(desastreActualizado.getUbicacion());
            return desastreRepository.save(desastre);
        }
        return null;
    }
    public void eliminarDesastre(String id) {
        desastreRepository.deleteById(id);
    }
}

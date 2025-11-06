package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // READ - todos CAMBIAR POR LISTA PROPIA
    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    // READ - por ID
    public Optional<Equipo> obtenerEquipoPorId(String id) {
        return equipoRepository.findById(id);
    }
    public Equipo actualizarEquipo(String id, Equipo equipoActualizado) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            equipo.setCantidadMiembros(equipoActualizado.getCantidadMiembros());
            equipo.setTipo(equipoActualizado.getTipo());
            return equipoRepository.save(equipo);
        }
        return null;
    }
    public void eliminarEquipo(String id) {
        equipoRepository.deleteById(id);
    }
}

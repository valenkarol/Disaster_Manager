package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Afectado;
import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EquipoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private ZonaRepository zonaRepository;
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // READ - todos CAMBIAR POR LISTA PROPIA
    public ListaSimpleEnlazada<Equipo> listarEquipos() {
        ListaSimpleEnlazada<Equipo> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Equipo> listaMongo = equipoRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Equipo equipo: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(equipo));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }
    public boolean asignarEquipoAZona(String zonaId, String equipoId, int cantidadMiembros) {

        Optional<Equipo> equipoOpt = equipoRepository.findById(equipoId);
        if (!equipoOpt.isPresent()) return false;

        Optional<Zona> zonaOpt = zonaRepository.findById(zonaId);
        if (!zonaOpt.isPresent()) return false;

        Equipo equipo = equipoOpt.get();

        // Actualizamos la asignación
        equipo.setCantidadMiembros(cantidadMiembros);
        equipo.setZonaAsignada(zonaId);

        equipoRepository.save(equipo);
        return true;
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

package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//importar lista pripia
import java.util.List;

@Service
public class UbicacionService {
    @Autowired
    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion crearUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public List<Ubicacion> listarUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public void eliminarUbicacion(String id) {
        ubicacionRepository.deleteById(id);
    }

    public boolean verificarUbicacion(String id) {
        return ubicacionRepository.existsById(id);
    }

    public Ubicacion actualizarUbicacion(String id, Ubicacion ubicacionActualizada) {
        Ubicacion ubicacion = ubicacionRepository.findById(id).orElseThrow();
        ubicacion.setTipoUbicacion(ubicacionActualizada.getTipoUbicacion());
        ubicacion.setCoordenadas(ubicacionActualizada.getCoordenadas());
        return ubicacionRepository.save(ubicacion);
    }

    public Ubicacion obtenerUbicacion(String id) {
        return ubicacionRepository.findById(id).orElse(null);
    }
}

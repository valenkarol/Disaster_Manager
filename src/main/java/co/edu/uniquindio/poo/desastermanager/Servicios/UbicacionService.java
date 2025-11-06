package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import org.springframework.stereotype.Service;
//importar lista pripia
import java.util.List;

@Service
public class UbicacionService {

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
}

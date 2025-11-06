package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RutaRepository;
import org.springframework.stereotype.Service;

//importar la lista enlazada propia
import java.util.List;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public List<Ruta> listarRutas() {
        return rutaRepository.findAll();
    }

    public void eliminarRuta(String id) {
        rutaRepository.deleteById(id);
    }
}

package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//importar lista propia
import java.util.List;

@Service
public class ZonaService {
    @Autowired
    private final ZonaRepository zonaRepository;

    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public Zona crearZona(Zona zona) {
        return zonaRepository.save(zona);
    }

    public List<Zona> listarZonas() {
        return zonaRepository.findAll();
    }

    public void eliminarZona(String id) {
        zonaRepository.deleteById(id);
    }
}

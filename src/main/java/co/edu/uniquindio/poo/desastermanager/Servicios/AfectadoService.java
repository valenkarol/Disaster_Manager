package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Afectado;
import co.edu.uniquindio.poo.desastermanager.Repositorio.AfectadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AfectadoService {

    private final AfectadoRepository afectadoRepository;

    public AfectadoService(AfectadoRepository afectadoRepository) {
        this.afectadoRepository = afectadoRepository;
    }

    public Afectado crearAfectado(Afectado afectado) {
        return afectadoRepository.save(afectado);
    }

    public List<Afectado> listarAfectados() {
        return afectadoRepository.findAll();
    }

    public void eliminarAfectado(String id) {
        afectadoRepository.deleteById(id);
    }
}


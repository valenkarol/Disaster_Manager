package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Afectado;
import co.edu.uniquindio.poo.desastermanager.Repositorio.AfectadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AfectadoService {

    @Autowired
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


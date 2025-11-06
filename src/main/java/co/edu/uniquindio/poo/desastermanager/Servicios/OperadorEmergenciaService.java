package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.OperadorEmergencia;
import co.edu.uniquindio.poo.desastermanager.Repositorio.OperadorEmergenciaRepository;
import org.springframework.stereotype.Service;
//importar lista propia
import java.util.List;

@Service
public class OperadorEmergenciaService {

    private final OperadorEmergenciaRepository operadorRepository;

    public OperadorEmergenciaService(OperadorEmergenciaRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    public OperadorEmergencia crearOperador(OperadorEmergencia operador) {
        return operadorRepository.save(operador);
    }

    public List<OperadorEmergencia> listarOperadores() {
        return operadorRepository.findAll();
    }

    public void eliminarOperador(String id) {
        operadorRepository.deleteById(id);
    }
}

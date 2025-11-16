package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Informe;
import co.edu.uniquindio.poo.desastermanager.Modelo.OperadorEmergencia;
import co.edu.uniquindio.poo.desastermanager.Repositorio.OperadorEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//importar lista propia
import java.util.List;

@Service
public class OperadorEmergenciaService {
    @Autowired
    private final OperadorEmergenciaRepository operadorRepository;

    public OperadorEmergenciaService(OperadorEmergenciaRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }
    public OperadorEmergencia login(String email, String password) {
        return operadorRepository.findByEmail(email)
                .filter(o -> o.getPassword().equals(password))
                .orElse(null);
    }

    public OperadorEmergencia crearOperador(OperadorEmergencia operador) {
        return operadorRepository.save(operador);
    }

    public ListaSimpleEnlazada<OperadorEmergencia> listarOperadores() {
        ListaSimpleEnlazada<OperadorEmergencia> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<OperadorEmergencia> listaMongo = operadorRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (OperadorEmergencia operadorEmergencia: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(operadorEmergencia));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }

    public void eliminarOperador(String id) {
        operadorRepository.deleteById(id);
    }
}

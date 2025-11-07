package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Administrador;
import co.edu.uniquindio.poo.desastermanager.Modelo.Afectado;
import co.edu.uniquindio.poo.desastermanager.Repositorio.AfectadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;

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

    public ListaSimpleEnlazada<Afectado> listarAfectados() {
        ListaSimpleEnlazada<Afectado> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Afectado> listaMongo = afectadoRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Afectado afectado : listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(afectado));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }

    public void eliminarAfectado(String id) {
        afectadoRepository.deleteById(id);
    }
}


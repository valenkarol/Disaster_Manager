package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
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

    public ListaSimpleEnlazada<Zona> listarZonas() {
        ListaSimpleEnlazada<Zona> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Zona> listaMongo = zonaRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Zona zona: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(zona));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }
    public void eliminarZona(String id) {
        zonaRepository.deleteById(id);
    }
}

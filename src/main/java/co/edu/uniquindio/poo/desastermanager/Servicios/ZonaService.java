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

    public Zona buscarZonaPorId(String id) {
        ListaSimpleEnlazada<Zona> lista = listarZonas();

        for (Zona zona : lista) {
            if (zona.getId().equals(id)) {
                return zona;
            }
        }
        return null;
    }


    public Zona crearZona(Zona zona) {
        return zonaRepository.save(zona);
    }

    public ListaSimpleEnlazada<Zona> listarZonas() {
        ListaSimpleEnlazada<Zona> listaPropia = new ListaSimpleEnlazada<>();
        List<Zona> listaMongo = zonaRepository.findAll();

        System.out.println("Zonas encontradas en MongoDB: " + listaMongo.size());

        for (Zona zona: listaMongo) {
            System.out.println("Zona: " + zona.getId() + " - " + zona.getNombreZona());
            listaPropia.agregarUltimo(new NodoLS<>(zona));
        }

        return listaPropia;
    }
    public void eliminarZona(String id) {
        zonaRepository.deleteById(id);
    }
}

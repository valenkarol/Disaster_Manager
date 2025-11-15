package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.MapaSimple;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//importar lista pripia
import java.util.List;

@Service
public class UbicacionService {
    @Autowired
    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion crearUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public ListaSimpleEnlazada<Ubicacion> listarUbicaciones() {
        ListaSimpleEnlazada<Ubicacion> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Ubicacion> listaMongo = ubicacionRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Ubicacion ubicacion: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(ubicacion));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }
// mapa propio
    public MapaSimple<String, Ubicacion> obtenerMapaUbicaciones() {

        MapaSimple<String, Ubicacion> mapa = new MapaSimple<>(50);
        List<Ubicacion> lista = ubicacionRepository.findAll();

        for (Ubicacion u : lista)
            mapa.put(u.getId(), u);

        return mapa;
    }


    public void eliminarUbicacion(String id) {
        ubicacionRepository.deleteById(id);
    }

    public boolean verificarUbicacion(String id) {
        return ubicacionRepository.existsById(id);
    }

    public Ubicacion actualizarUbicacion(String id, Ubicacion ubicacionActualizada) {
        Ubicacion ubicacion = ubicacionRepository.findById(id).orElseThrow();
        ubicacion.setTipoUbicacion(ubicacionActualizada.getTipoUbicacion());
        ubicacion.setCoordenadas(ubicacionActualizada.getCoordenadas());
        return ubicacionRepository.save(ubicacion);
    }

    public Ubicacion obtenerUbicacion(String id) {
        return ubicacionRepository.findById(id).orElse(null);
    }
}

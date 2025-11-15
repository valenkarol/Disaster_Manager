package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.*;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RutaRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//importar la lista enlazada propia
import java.util.*;

@Service
public class RutaService {
    @Autowired
    private final RutaRepository rutaRepository;

    @Autowired // para el metodo construir grafo
    private UbicacionRepository ubicacionRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public ListaSimpleEnlazada<Ruta> listarRutas() {
        ListaSimpleEnlazada<Ruta> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Ruta> listaMongo = rutaRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Ruta ruta: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(ruta));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }
    private GrafoDirigido construirGrafo() {

        GrafoDirigido grafo = new GrafoDirigido(50);

        // 1. Obtener todas las ubicaciones desde Mongo
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();

        // Agregar nodos al grafo
        for (Ubicacion u : ubicaciones) {
            grafo.agregarNodo(u);
        }

        // 2. Obtener las rutas desde Mongo
        List<Ruta> rutas = rutaRepository.findAll();

        for (Ruta r : rutas) {
            grafo.agregarArista(
                    r.getOrigen(),
                    r.getDestino(),
                    r.getDistancia()
            );
        }

        return grafo;
    }

    public MapaSimple<String, ListaSimpleEnlazada<Arista>> obtenerMapaGrafo() {

        MapaSimple<String, ListaSimpleEnlazada<Arista>> grafo = new MapaSimple<>(50);

        List<Ruta> rutas = rutaRepository.findAll();

        for (Ruta r : rutas) {

            String origen = r.getOrigen().getId();
            String destino = r.getDestino().getId();

            Arista arista = new Arista(destino, r.getDistancia());

            if (!grafo.containsKey(origen)) {
                grafo.put(origen, new ListaSimpleEnlazada<>());
            }

            grafo.get(origen).agregarUltimo(new NodoLS<>(arista));
        }

        return grafo;
    }

    public void eliminarRuta(String id) {
        rutaRepository.deleteById(id);
    }


    //SUPUESTAMENTE DIJKSTRA - NO LO SEEE

    public double rutaMasCorta(Ubicacion origen, Ubicacion destino) {

        GrafoDirigido grafo = construirGrafo();

        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();

        MapaSimple<String, Double> dist = new MapaSimple<>(100);
        MapaSimple<String, Boolean> visitado = new MapaSimple<>(100);

        // Inicializar
        for (Ubicacion u : ubicaciones) {
            dist.put(u.getId(), Double.MAX_VALUE);
            visitado.put(u.getId(), false);
        }

        dist.put(origen.getId(), 0.0);

        while (true) {

            String actualId = null;
            double min = Double.MAX_VALUE;

            // Obtener nodo NO visitado más cercano
            for (Ubicacion u : ubicaciones) {

                double d = dist.get(u.getId());
                boolean v = visitado.get(u.getId());

                if (!v && d < min) {
                    min = d;
                    actualId = u.getId();
                }
            }

            if (actualId == null) break;
            if (actualId.equals(destino.getId())) break;

            visitado.put(actualId, true);

            //
            ListaSimpleEnlazada<Arista> lista = grafo.obtenerAdyacentes(actualId);
            if (lista == null) continue;

            for (Arista a : lista) {

                String vecinoId = a.getDestino();
                double nuevaDist = dist.get(actualId) + a.getPeso();

                if (nuevaDist < dist.get(vecinoId)) {
                    dist.put(vecinoId, nuevaDist);
                }
            }
        }

        return dist.get(destino.getId());
    }

}

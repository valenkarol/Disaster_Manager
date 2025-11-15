package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//importar la lista enlazada propia
import java.util.*;

@Service
public class RutaService {
    @Autowired
    private final RutaRepository rutaRepository;

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
    public void eliminarRuta(String id) {
        rutaRepository.deleteById(id);
    }


    //SUPUESTAMENTE DIJKSTRA - NO LO SEEE
    // MAP, HASHMAP, ARRAYLIST, SET, HASHSET, KEYSET PROPIOS

    public double rutaMasCorta(Ubicacion origen, Ubicacion destino) {
        List<Ruta> todasLasRutas = rutaRepository.findAll();

        // Construimos el grafo MAP Y HASHMAP Y ARRAYLIST PROPIOS
        Map<Ubicacion, List<Ruta>> grafo = new HashMap<>();
        for (Ruta ruta : todasLasRutas) {
            grafo.computeIfAbsent(ruta.getOrigen(), k -> new ArrayList<>()).add(ruta);
        }

        // Inicializamos distancias y conjunto visitado
        Map<Ubicacion, Double> distancias = new HashMap<>();
        Set<Ubicacion> visitados = new HashSet<>();

        for (Ubicacion u : grafo.keySet()) {
            distancias.put(u, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);

        while (visitados.size() < grafo.size()) {
            // Nodo con menor distancia no visitado
            Ubicacion actual = distancias.entrySet().stream()
                    .filter(e -> !visitados.contains(e.getKey()))
                    .min(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);

            if (actual == null) break; // No hay más nodos alcanzables
            if (actual.equals(destino)) break; // Llegamos al destino

            visitados.add(actual);

            for (Ruta ruta : grafo.getOrDefault(actual, new ArrayList<>())) {
                Ubicacion vecino = ruta.getDestino();
                double nuevaDist = distancias.get(actual) + ruta.getDistancia();

                if (nuevaDist < distancias.getOrDefault(vecino, Double.MAX_VALUE)) {
                    distancias.put(vecino, nuevaDist);
                }
            }
        }

        return distancias.getOrDefault(destino, Double.MAX_VALUE);
    }
}

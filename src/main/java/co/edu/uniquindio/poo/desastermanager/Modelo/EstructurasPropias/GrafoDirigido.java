package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;

public class GrafoDirigido {

    private MapaSimple<String, ListaSimpleEnlazada<Arista>> adyacencias;

    public GrafoDirigido(int capacidad) {
        adyacencias = new MapaSimple<>(capacidad);
    }

    public void agregarNodo(Ubicacion u) {
        if (adyacencias.get(u.getId()) == null) {
            adyacencias.put(u.getId(), new ListaSimpleEnlazada<>());
        }
    }

    public void agregarArista(Ubicacion origen, Ubicacion destino, double peso) {

        ListaSimpleEnlazada<Arista> lista = adyacencias.get(origen.getId());

        if (lista == null) {
            lista = new ListaSimpleEnlazada<>();
            adyacencias.put(origen.getId(), lista);
        }

        // La arista usa el ID del destino, no el objeto destino
        lista.agregarUltimo(new NodoLS<>(new Arista(destino.getId(), peso)));
    }

    public ListaSimpleEnlazada<Arista> obtenerAdyacentes(String idOrigen) {
        return adyacencias.get(idOrigen);
    }
}


package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;

public class ArbolDistribucion {

    private NodoArbol raiz;

    public ArbolDistribucion() {
        this.raiz = null;
    }

    // -------------------------------
    // INSERTAR (ordenado por riesgo)
    // -------------------------------
    public void insertar(Zona zona, Recurso recurso) {
        raiz = insertarNodo(raiz, zona, recurso);
    }

    private NodoArbol insertarNodo(NodoArbol actual, Zona zona, Recurso recurso) {
        if (actual == null) {
            return new NodoArbol(zona, recurso);
        }

        // Ordenar por nivel de riesgo (mayor riesgo se va a la izquierda)
        if (zona.getNivelRiesgo() > actual.getZona().getNivelRiesgo()) {
            actual.setIzquierda(insertarNodo(actual.getIzquierda(), zona, recurso));
        } else {
            actual.setDerecha(insertarNodo(actual.getDerecha(), zona, recurso));
        }

        return actual;
    }

    // -------------------------------
    // BUSCAR ZONA POR ID
    // -------------------------------
    public NodoArbol buscar(String idZona) {
        return buscarNodo(raiz, idZona);
    }

    private NodoArbol buscarNodo(NodoArbol actual, String idZona) {
        if (actual == null) return null;

        if (actual.getZona().getId().equals(idZona)) {
            return actual;
        }

        NodoArbol left = buscarNodo(actual.getIzquierda(), idZona);
        if (left != null) return left;

        return buscarNodo(actual.getDerecha(), idZona);
    }

    // -------------------------------
    // RECORRIDO (INORDEN)
    // -------------------------------
    public void recorrerInorden() {
        recorrerInorden(raiz);
    }

    private void recorrerInorden(NodoArbol nodo) {
        if (nodo != null) {
            recorrerInorden(nodo.getIzquierda());
            System.out.println("Zona: " + nodo.getZona().getNombreZona() +
                    " | Riesgo: " + nodo.getZona().getNivelRiesgo() +
                    " | Recurso: " + nodo.getRecursoAsignado().getTipo().name());
            recorrerInorden(nodo.getDerecha());
        }
    }
}

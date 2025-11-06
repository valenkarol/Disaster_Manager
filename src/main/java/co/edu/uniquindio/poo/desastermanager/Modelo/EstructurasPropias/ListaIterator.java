package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

import java.util.Iterator;

public class ListaIterator<T extends Comparable<T>> implements Iterator<T> {
    private NodoLS<T> actual;

    public ListaIterator(NodoLS<T> primero) {
        this.actual = primero;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;  // en lugar de lanzar excepción
        }
        T elemento = actual.getDato();
        actual = actual.getProximo();
        return elemento;
    }
}

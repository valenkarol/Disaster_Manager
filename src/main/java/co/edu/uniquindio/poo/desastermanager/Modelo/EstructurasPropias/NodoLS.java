package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

public class NodoLS<T extends Comparable<T>> {
    private T dato;
    private NodoLS<T> proximo;
    public NodoLS(T dato){
        this.dato = dato;
        proximo = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLS<T> getProximo() {
        return proximo;
    }

    public void setProximo(NodoLS<T> proximo) {
        this.proximo = proximo;
    }
    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", proximo=" + proximo +
                '}';
    }

}

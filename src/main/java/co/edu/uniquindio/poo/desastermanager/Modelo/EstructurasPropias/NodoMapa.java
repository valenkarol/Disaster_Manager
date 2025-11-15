package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

//Guarda la estructura del mapa
public class NodoMapa<K, V> {
    private EntradaMapa<K, V> entrada;
    private NodoMapa<K, V> siguiente;

    public NodoMapa(EntradaMapa<K, V> entrada) {
        this.entrada = entrada;
        this.siguiente = null;
    }

    public EntradaMapa<K, V> getEntrada() {
        return entrada;
    }

    public NodoMapa<K, V> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMapa<K, V> siguiente) {
        this.siguiente = siguiente;
    }
}

package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

//Guarda los datos del mapa. Es como el "objeto"
public class EntradaMapa<K, V> {
    private K llave;
    private V valor;

    public EntradaMapa(K llave, V valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public K getLlave() {
        return llave;
    }

    public void setLlave(K llave) {
        this.llave = llave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
}

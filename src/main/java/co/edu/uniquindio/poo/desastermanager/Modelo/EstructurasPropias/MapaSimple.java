package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

public class MapaSimple<K, V> {
    private NodoMapa<K, V>[] tabla;
    private int capacidad;
    private int tamaño;

    public MapaSimple(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new NodoMapa[capacidad];
        this.tamaño = 0;
    }

    private int hash(K llave) {
        return Math.abs(llave.hashCode()) % capacidad;
    }

    public void put(K llave, V valor) {
        int index = hash(llave);
        NodoMapa<K, V> actual = tabla[index];

        // Si ya existe, reemplazar valor
        while (actual != null) {
            if (actual.getEntrada().getLlave().equals(llave)) {
                actual.getEntrada().setValor(valor);
                return;
            }
            actual = actual.getSiguiente();
        }

        // No existe → insertar al inicio
        EntradaMapa<K, V> entrada = new EntradaMapa<>(llave, valor);
        NodoMapa<K, V> newNode = new NodoMapa<>(entrada);

        newNode.setSiguiente(tabla[index]);
        tabla[index] = newNode;

        tamaño++;
    }

    public V get(K llave) {
        int index = hash(llave);
        NodoMapa<K, V> actual = tabla[index];

        while (actual != null) {
            if (actual.getEntrada().getLlave().equals(llave)) {
                return actual.getEntrada().getValor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean containsKey(K llave) {
        return get(llave) != null;
    }

    public boolean remove(K llave) {
        int index = hash(llave);
        NodoMapa<K, V> actual = tabla[index];
        NodoMapa<K, V> anterior = null;

        while (actual != null) {
            if (actual.getEntrada().getLlave().equals(llave)) {
                if (anterior == null) {
                    tabla[index] = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                tamaño--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int size() {
        return tamaño;
    }

    public void mostrar() {
        for (int i = 0; i < capacidad; i++) {
            System.out.print("Bucket " + i + ": ");
            NodoMapa<K, V> actual = tabla[i];
            if (actual == null) {
                System.out.println("vacío");
                continue;
            }
            while (actual != null) {
                System.out.print("[" + actual.getEntrada().getLlave() + " → " + actual.getEntrada().getValor() + "] ");
                actual = actual.getSiguiente();
            }
            System.out.println();
        }
    }
}


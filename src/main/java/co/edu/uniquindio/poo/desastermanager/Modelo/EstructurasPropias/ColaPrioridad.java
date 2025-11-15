package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

public class ColaPrioridad<T extends Comparable<T>> {

    private ListaSimpleEnlazada<T> lista;

    public ColaPrioridad() {
        lista = new ListaSimpleEnlazada<>();
    }

    // -------------------------------
    // INSERTAR ORDENADO (PRIORIDAD)
    // -------------------------------
    public void insertar(T elemento) {

        NodoLS<T> nuevoNodo = new NodoLS<>(elemento);

        // Si está vacía → agregar al inicio
        if (lista.estaVacia()) {
            lista.agregarPrimero(nuevoNodo);
            return;
        }

        int index = 0;
        for (T actual : lista) {

            // Si elemento tiene MAYOR prioridad que el actual → insertar antes
            if (elemento.compareTo(actual) > 0) {
                lista.agregarEnPosicion(index, nuevoNodo);
                return;
            }
            index++;
        }

        // Si no es mayor que ninguno → va al final
        lista.agregarUltimo(nuevoNodo);
    }

    // -------------------------------
    // SACAR EL MÁS PRIORITARIO
    // -------------------------------
    public T extraer() {
        if (lista.estaVacia())
            return null;

        T primero = lista.iterator().next();
        lista.eliminar(primero);
        return primero;
    }

    // -------------------------------
    // VER EL PRIMERO
    // -------------------------------
    public T primero() {
        if (lista.estaVacia()) return null;
        return lista.iterator().next();
    }

    public boolean estaVacia() {
        return lista.estaVacia();
    }

    public int tamaño() {
        return lista.tamaño();
    }
}

package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;

public class Arista implements Comparable<Arista> {

    private String destino;
    private double peso;

    public Arista(String destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public int compareTo(Arista otra) {
        // Comparación mínima solo para cumplir la interfaz
        return Double.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return "Arista{destino='" + destino + "', peso=" + peso + "}";
    }
}


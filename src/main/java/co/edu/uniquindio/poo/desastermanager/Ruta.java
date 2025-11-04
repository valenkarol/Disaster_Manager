package co.edu.uniquindio.poo.desastermanager;

public class Ruta {
    private Ubicacion origen;
    private Ubicacion destino;
    private double distancia;
    private String estadoRuta;

    public Ruta(Ubicacion origen, Ubicacion destino, double distancia, String estadoRuta) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.estadoRuta = estadoRuta;
    }

    public Ubicacion getOrigen() {
        return origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public String getEstadoRuta() {
        return estadoRuta;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estadoRuta = nuevoEstado;
    }
}

package co.edu.uniquindio.poo.desastermanager;

public class Ubicacion {
    private String coordenadas;
    private TipoUbicacion tipoUbicacion; // CIUDAD, REFUGIO, CENTRO DE AYUDA

    public Ubicacion(String coordenadas, TipoUbicacion tipoUbicacion) {
        this.coordenadas = coordenadas;
        this.tipoUbicacion = tipoUbicacion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public TipoUbicacion getTipoUbicacion() {
        return tipoUbicacion;
    }

    @Override
    public String toString() {
        return tipoUbicacion + " (" + coordenadas + ")";
    }
}


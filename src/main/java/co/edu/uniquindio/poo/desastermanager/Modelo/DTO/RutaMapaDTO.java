package co.edu.uniquindio.poo.desastermanager.Modelo.DTO;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;

public class RutaMapaDTO {
    private String id;
    private String origenId;
    private String destinoId;
    private double distancia;
    private String estadoRuta;

    public RutaMapaDTO(Ruta ruta) {
        this.id = ruta.getId();
        this.origenId = ruta.getOrigen().getId();
        this.destinoId = ruta.getDestino().getId();
        this.distancia = ruta.getDistancia();
        this.estadoRuta = ruta.getEstadoRuta();
    }

    // getters y setters
    public String getId() { return id; }
    public String getOrigenId() { return origenId; }
    public String getDestinoId() { return destinoId; }
    public double getDistancia() { return distancia; }
    public String getEstadoRuta() { return estadoRuta; }
}

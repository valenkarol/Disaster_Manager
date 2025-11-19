package co.edu.uniquindio.poo.desastermanager.Modelo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RutaMapaResponseDTO {
    private String id;
    private String origen;   // "lat,lng"
    private String destino;  // "lat,lng"
    private double distancia;
    private String estadoRuta;
}
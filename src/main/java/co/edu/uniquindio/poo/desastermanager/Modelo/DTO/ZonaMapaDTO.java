package co.edu.uniquindio.poo.desastermanager.Modelo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonaMapaDTO {
    private String id;
    private String nombreZona;
    private int nivelRiesgo;
}

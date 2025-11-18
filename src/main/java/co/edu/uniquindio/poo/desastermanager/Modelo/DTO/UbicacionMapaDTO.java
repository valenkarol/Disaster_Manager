package co.edu.uniquindio.poo.desastermanager.Modelo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionMapaDTO {
    private String id;
    private String coordenadas;
    private String tipoUbicacion;
}

package co.edu.uniquindio.poo.desastermanager.Modelo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionMapaDTO {
    private String id;
    private String nombre;
    private String direccion;

    // "lat,lng"
    private String coordenadas;

    // TipoUbicacion en String (CIUDAD, HOSPITAL, VIVIENDA, etc.)
    private String tipoUbicacion;

    // "high", "medium", "low"
    private String prioridad;

    private int nivelRiesgo;
    private int recursos;
    private int evacuados;
    private int equipos;


}

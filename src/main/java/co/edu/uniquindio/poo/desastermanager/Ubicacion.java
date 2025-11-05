package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ubicaciones")
public class Ubicacion {
    @Id
    private String coordenadas;
    private TipoUbicacion tipoUbicacion;
}


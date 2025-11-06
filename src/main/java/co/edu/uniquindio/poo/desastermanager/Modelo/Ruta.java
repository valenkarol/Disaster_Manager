package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rutas")

public class Ruta {
    @Id
    private Ubicacion origen;
    private Ubicacion destino;
    private double distancia;
    private String estadoRuta;
}

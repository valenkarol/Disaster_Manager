package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ubicaciones")
public class Ubicacion implements Comparable<Ubicacion>{
    @Id
    private String coordenadas;
    private TipoUbicacion tipoUbicacion;

    @Override
    public int compareTo(Ubicacion otra) {
        if (otra == null) return 1;

        // 1. Por tipo
        int comparacion = this.tipoUbicacion.compareTo(otra.tipoUbicacion);

        // 2. Si tipo igual, por coordenadas
        if (comparacion == 0) {
            comparacion = this.coordenadas.compareToIgnoreCase(otra.coordenadas);
        }

        return comparacion;
    }

}



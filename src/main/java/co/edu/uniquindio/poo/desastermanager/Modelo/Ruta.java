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

public class Ruta implements Comparable<Ruta>{
    @Id
    private Ubicacion origen;
    private Ubicacion destino;
    private double distancia;
    private String estadoRuta;

    @Override
    public int compareTo(Ruta otra) {
        if (otra == null) return 1;

        // 1. Comparar por distancia
        int comparacion = Double.compare(this.distancia, otra.distancia);

        // 2. Si las distancias son iguales, comparar estado
        if (comparacion == 0) {
            comparacion = this.estadoRuta.compareToIgnoreCase(otra.estadoRuta);
        }

        // 3. Si estado igual, comparar origen
        if (comparacion == 0) {
            comparacion = this.origen.getCoordenadas()
                    .compareToIgnoreCase(otra.origen.getCoordenadas());
        }

        // 4. Si origen igual, comparar destino
        if (comparacion == 0) {
            comparacion = this.destino.getCoordenadas()
                    .compareToIgnoreCase(otra.destino.getCoordenadas());
        }

        return comparacion;
    }

}

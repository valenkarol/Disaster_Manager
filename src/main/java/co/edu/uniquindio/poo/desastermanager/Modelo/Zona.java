package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "zonas")
public class Zona implements Comparable<Zona>{
    @Id
    private String id;
    private String nombreZona;
    private int nivelRiesgo;

    @Override
    public int compareTo(Zona otra) {
        if (otra == null) return 1;

        // 1. Mayor riesgo primero → orden descendente
        int comparacion = Integer.compare(otra.nivelRiesgo, this.nivelRiesgo);

        // 2. Si igual, comparar por nombre
        if (comparacion == 0) {
            comparacion = this.nombreZona.compareToIgnoreCase(otra.nombreZona);
        }

        // 3. Si aún igual, comparar por id
        if (comparacion == 0) {
            comparacion = this.id.compareTo(otra.id);
        }

        return comparacion;
    }

}

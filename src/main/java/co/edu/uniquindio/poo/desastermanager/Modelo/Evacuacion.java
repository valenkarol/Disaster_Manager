package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "evacuaciones")
public class Evacuacion implements Comparable<Evacuacion>{

    @Id
    private String id;
    private int numeroAfectados;
    private int prioridad;

    @Override
    public int compareTo(Evacuacion otra) {
        if (otra == null) return 1;

        // 1. Comparar por prioridad (mayor prioridad primero)
        int comparacion = Integer.compare(otra.prioridad, this.prioridad);

        // 2. Si la prioridad es igual, comparar por número de afectados (más afectados primero)
        if (comparacion == 0) {
            comparacion = Integer.compare(otra.numeroAfectados, this.numeroAfectados);
        }

        // 3. Si también son iguales, comparar por ID para mantener orden consistente
        if (comparacion == 0) {
            comparacion = this.id.compareTo(otra.id);
        }

        return comparacion;
    }

}

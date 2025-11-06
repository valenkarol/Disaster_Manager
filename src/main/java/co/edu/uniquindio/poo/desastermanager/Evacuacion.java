package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "evacuaciones")
public class Evacuacion {
    private String id;
    private int numeroAfectados;
    private int prioridad;

}

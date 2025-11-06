package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recursos")
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private int cantidad;
    private TipoRecurso tipo;

    public void agregarRecurso(Recurso recurso) {
        this.cantidad += recurso.getCantidad();
    }

    public int calcularTotalRecursos() {
        return this.cantidad;
    }
}


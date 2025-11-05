package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recurso {

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


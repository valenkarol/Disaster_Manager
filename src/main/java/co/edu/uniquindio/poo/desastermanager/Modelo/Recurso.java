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
@Document(collection = "recursos")
public class Recurso implements Comparable<Recurso>{

    @Id
    private String id;
    private String nombre;
    private int cantidad;
    private TipoRecurso tipo;

    @Override
    public int compareTo(Recurso otro) {
        if (otro == null) return 1;

        // 1. Comparar por tipo de recurso
        int comparacion = this.tipo.compareTo(otro.tipo);

        // 2. Si es igual, comparar por nombre
        if (comparacion == 0) {
            comparacion = this.nombre.compareToIgnoreCase(otro.nombre);
        }

        // 3. Si es igual, comparar por cantidad
        if (comparacion == 0) {
            comparacion = Integer.compare(this.cantidad, otro.cantidad);
        }

        // 4. Si todo es igual, comparar por id
        if (comparacion == 0) {
            comparacion = this.id.compareTo(otro.id);
        }

        return comparacion;
    }


    public void agregarRecurso(Recurso recurso) {
        this.cantidad += recurso.getCantidad();
    }

    public int calcularTotalRecursos() {
        return this.cantidad;
    }
}


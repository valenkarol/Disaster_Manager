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
@Document(collection = "equipos")
public class Equipo implements Comparable<Equipo>{

    @Id
    private int cantidadMiembros;
    private TipoEquipoRescate tipo;

    @Override
    public int compareTo(Equipo otro) {
        if (otro == null) return 1;

        // 1. Comparar por cantidad de miembros
        int comparacion = Integer.compare(this.cantidadMiembros, otro.cantidadMiembros);

        // 2. Si son iguales, comparar por tipo
        if (comparacion == 0) {
            comparacion = this.tipo.compareTo(otro.tipo);
        }

        return comparacion;
    }


    // PREGUNTAR AL ASESOR SI ESTOS VAN
    public void crear() {
        System.out.println("Equipo creado: " + this);
    }

    public void leer() {
        System.out.println("Leyendo información del equipo...");
    }

    public void actualizar(Equipo equipoActualizado) {
        this.cantidadMiembros = equipoActualizado.getCantidadMiembros();
        this.tipo = equipoActualizado.getTipo();
    }

    public void eliminar() {
        System.out.println("Equipo eliminado: " + this);
    }
}

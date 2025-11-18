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
    private String id;
    private TipoEquipoRescate tipo;
    private int cantidadMiembros;
    private String zonaAsignada;

    @Override
    public int compareTo(Equipo otro) {
        if (otro == null) return 1;

        int compare = Integer.compare(this.cantidadMiembros, otro.cantidadMiembros);
        if (compare != 0) return compare;

        return this.id.compareTo(otro.id); // desempate
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

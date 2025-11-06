package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "equipos")
public class Equipo {

    @Id
    private int cantidadMiembros;
    private TipoEquipoRescate tipo;

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

package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
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

package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)//?
@Document(collection = "afectados")
public class Afectado extends Persona implements Comparable<Afectado>{

    @Id
    private String idAfectado;
    private String estadoSalud;

    @Override
    public int compareTo(Afectado otro) {
        if (otro == null) return 1;

        // Compara por nombre
        int comparacion = this.getNombrePersona().compareToIgnoreCase(otro.getNombrePersona());


        // Si ambos son iguales, compara por idAfectado
        if (comparacion == 0) {
            comparacion = this.idAfectado.compareToIgnoreCase(otro.idAfectado);
        }

        return comparacion;
    }

    public void registrarAfectado() {
        System.out.println("Registrando afectado con estado de salud: " + estadoSalud);
    }

    public void crear() {
        System.out.println("Afectado creado: " + getNombrePersona());
    }

    public void leer() {
        System.out.println("Leyendo datos del afectado: " + getIdPersona());
    }

    public void actualizar() {
        System.out.println("Actualizando afectado: " + getNombrePersona());
    }

    public void eliminar() {
        System.out.println("Afectado eliminado: " + getNombrePersona());
    }
}

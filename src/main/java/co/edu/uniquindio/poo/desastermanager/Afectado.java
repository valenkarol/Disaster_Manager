package co.edu.uniquindio.poo.desastermanager;

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
public class Afectado extends Persona {

    @Id
    private String idAfectado;
    private String estadoSalud;

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

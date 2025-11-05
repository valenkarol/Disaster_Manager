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
@EqualsAndHashCode(callSuper = true) //que es esto
@Document(collection = "administradores")
public class Administrador extends Persona {
    @Id
    private String idAdministrador;

    public String generarInforme() {
        return "Informe generado por el administrador " + getNombrePersona() + " " + getApellidosPersona();
    }

    public void crear() {
        System.out.println("Administrador creado: " + getNombrePersona());
    }

    public void leer() {
        System.out.println("Leyendo datos del administrador: " + getIdPersona());
    }

    public void actualizar() {
        System.out.println("Actualizando administrador: " + getNombrePersona());
    }

    public void eliminar() {
        System.out.println("Administrador eliminado: " + getNombrePersona());
    }
}

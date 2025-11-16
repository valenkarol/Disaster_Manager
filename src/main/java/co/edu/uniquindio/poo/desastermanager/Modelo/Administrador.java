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
@EqualsAndHashCode(callSuper = true) //que es esto
@Document(collection = "administradores")
public class Administrador extends Persona implements Comparable<Administrador> {


    private String email;
    private String password;

    @Override
    public int compareTo(Administrador otro) {
        if (otro == null) return 1;

        int comparacion = this.getNombrePersona()
                .compareToIgnoreCase(otro.getNombrePersona());

        if (comparacion == 0) {
            return this.email.compareToIgnoreCase(otro.email);
        }

        return comparacion;
    }

    public String generarInforme() {
        return "Informe generado por el administrador " + getNombrePersona() + " " + getApellidosPersona();
    }

    public void crear() {
        System.out.println("Administrador creado: " + getNombrePersona());
    }

    public void leer() {
        System.out.println("Leyendo datos del administrador: " + getId());
    }

    public void actualizar() {
        System.out.println("Actualizando administrador: " + getNombrePersona());
    }

    public void eliminar() {
        System.out.println("Administrador eliminado: " + getNombrePersona());
    }
}

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
    @Id
    private String idAdministrador;

    private String email;
    private String password;

    @Override
    public int compareTo(Administrador otro) {
        if (otro == null) return 1;

        // Compara por nombre, y si son iguales, por ID
        int comparacion = this.getNombrePersona().compareToIgnoreCase(otro.getNombrePersona());
        if (comparacion == 0) {
            // Si los nombres son iguales, compara por ID
            return this.idAdministrador.compareToIgnoreCase(otro.idAdministrador);
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
        System.out.println("Leyendo datos del administrador: " + getIdPersona());
    }

    public void actualizar() {
        System.out.println("Actualizando administrador: " + getNombrePersona());
    }

    public void eliminar() {
        System.out.println("Administrador eliminado: " + getNombrePersona());
    }
}

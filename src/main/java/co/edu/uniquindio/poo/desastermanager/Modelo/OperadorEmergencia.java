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
@EqualsAndHashCode(callSuper = true)//que es
@Document(collection = "operadoresEmergencia")
public class OperadorEmergencia extends Persona implements Comparable<OperadorEmergencia> {



    private String email;
    private String password;

    @Override
    public int compareTo(OperadorEmergencia otro) {
        if (otro == null) return 1;

        int comparacion = this.getNombrePersona()
                .compareToIgnoreCase(otro.getNombrePersona());

        if (comparacion == 0) {
            return this.email.compareToIgnoreCase(otro.email);
        }
        return comparacion;
    }

    public void monitorearZona(Zona zona) {
        System.out.println("Monitoreando zona: " + zona.getNombreZona() + " (riesgo " + zona.getNivelRiesgo() + ")");
    }

    public void verificarEstadoRuta(Ruta ruta) {
        System.out.println("Ruta desde " + ruta.getOrigen().getCoordenadas() + " hasta " + ruta.getDestino().getCoordenadas() +
                " está " + ruta.getEstadoRuta());
    }

    public void crear() {
        System.out.println("Operador de emergencia creado: " + getNombrePersona());
    }

    public void leer() {
        System.out.println("Leyendo datos del operador: " + getIdPersona());
    }

    public void actualizar() {
        System.out.println("Actualizando operador de emergencia: " + getNombrePersona());
    }

    public void eliminar() {
        System.out.println("Operador eliminado: " + getNombrePersona());
    }
}

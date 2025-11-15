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

    @Id
    private String idOperador;

    @Override
    public int compareTo(OperadorEmergencia otro) {
        if (otro == null) return 1;

        return this.idOperador.compareTo(otro.idOperador);
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

package co.edu.uniquindio.poo.desastermanager.Modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "desastres")
public class Desastre implements Comparable<Desastre> {

    @Id
    private String id;
    private LocalDateTime fecha;
    private Ubicacion ubicacion;

    @Override
    public int compareTo(Desastre otro) {
        if (otro == null) return 1;

        int comparacion = this.getUbicacion().getCoordenadas()
                .compareToIgnoreCase(otro.getUbicacion().getCoordenadas());

        if (comparacion == 0) {
            comparacion = this.id.compareToIgnoreCase(otro.id);
        }

        return comparacion;
    }


    // Constructor explícito preguntar si es necesario - chat dice que en algunos casos si como en este caso
    public Desastre(LocalDateTime fecha, Ubicacion ubicacion) {
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }
    public void DondeOcurrio(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        System.out.println("El desastre ocurrió en: " + ubicacion);
    }
    public void ObtenerFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        System.out.println("Fecha del desastre establecida: " + fecha);
    }
}

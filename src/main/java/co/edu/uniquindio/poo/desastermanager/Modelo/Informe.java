package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "informes")
public class Informe implements Comparable<Informe>{
    @Id
    private String id;
    private LocalDateTime fecha;
    private String resumen;

    @Override
    public int compareTo(Informe otro) {
        if (otro == null) return 1;

        // 1. Comparar por fecha (más recientes primero)
        int comparacion = otro.fecha.compareTo(this.fecha);

        // 2. Si las fechas son iguales, comparar por id
        if (comparacion == 0) {
            comparacion = this.id.compareTo(otro.id);
        }

        return comparacion;
    }


    // Métodos lógicos ¿ESTOS VAN?
    public void crear() {
        System.out.println("Informe creado en fecha: " + fecha);
    }

    public Informe leer() {
        System.out.println("Leyendo informe con ID: " + id);
        return this;
    }

    public void actualizar() {
        System.out.println("Informe actualizado");
    }

    public void eliminar() {
        System.out.println("Informe eliminado");
    }
//hasta aca
    public String generarResumen() {
        return "Resumen del informe generado el " + fecha;
    }

}

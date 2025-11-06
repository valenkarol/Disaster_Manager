package co.edu.uniquindio.poo.desastermanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "informes")
public class Informe {
    @Id
    private String id;
    private LocalDateTime fecha;

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

    public void exportarPDF() {
        System.out.println("Exportando informe " + id + " a PDF...");
    }
}

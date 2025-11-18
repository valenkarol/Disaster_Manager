package co.edu.uniquindio.poo.desastermanager.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estadistica implements Comparable<Estadistica> {

    private Zona zona;                   // Referencia a la zona
    private int totalPersonas;           // Personas en la zona
    private int personasEvacuadas;       // Personas evacuadas
    private int personasPendientes;      // Personas pendientes
    private int recursosDisponibles;     // Recursos asignados a la zona

    @Override
    public int compareTo(Estadistica otra) {
        if (otra == null) return 1;
        return Integer.compare(this.totalPersonas, otra.totalPersonas);
    }
}

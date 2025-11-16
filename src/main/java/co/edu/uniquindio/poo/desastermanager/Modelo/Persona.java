package co.edu.uniquindio.poo.desastermanager.Modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personas")

public class Persona {
    @Id
    private String id;
    private String nombrePersona;
    private String apellidosPersona;
    private int edad;

}


package co.edu.uniquindio.poo.desastermanager.Servicios;


import co.edu.uniquindio.poo.desastermanager.Persona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {


    @Autowired
    private PersonaRepository personaRepository;

    public static Persona guardarUsuario(String nombre, String apellido) {
        Persona persona = new Persona();
        persona.se

    }
}

package co.edu.uniquindio.poo.desastermanager.Servicios;


import co.edu.uniquindio.poo.desastermanager.Modelo.Persona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {


    @Autowired
    private PersonaRepository personaRepository;

    public Persona guardarUsuario(String nombre, String apellido) {
        Persona persona = new Persona();
        persona.setNombrePersona(nombre);
        persona.setApellidosPersona(apellido);
        persona.setEdad(18); //TODO: Edad debe ser pedida


        return personaRepository.save(persona);

    }
}

package co.edu.uniquindio.poo.desastermanager.Controlador;


import co.edu.uniquindio.poo.desastermanager.Persona;
import co.edu.uniquindio.poo.desastermanager.Servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    PersonaService personaService;


    @PostMapping
    public ResponseEntity<Persona> crearPersona(
            @RequestParam String nombre,
            @RequestParam String apellido) {
        try {
            Persona usuarioGuardado = personaService.guardarUsuario(nombre,apellido);
            return  new ResponseEntity<>(usuarioGuardado,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Afectado;
import co.edu.uniquindio.poo.desastermanager.Servicios.AfectadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//cambiar por lista propia
import java.util.List;

@RestController
@RequestMapping("/afectados")
public class AfectadoController {

    private final AfectadoService afectadoService;

    public AfectadoController(AfectadoService afectadoService) {
        this.afectadoService = afectadoService;
    }

    // Crear un nuevo afectado
    @PostMapping
    public ResponseEntity<Afectado> crearAfectado(@RequestBody Afectado afectado) {
        try {
            Afectado afectadoGuardado = afectadoService.crearAfectado(afectado);
            return new ResponseEntity<>(afectadoGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar todos los afectados
    @GetMapping
    public ResponseEntity<List<Afectado>> listarAfectados() {
        try {
            List<Afectado> lista = afectadoService.listarAfectados();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un afectado por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAfectado(@PathVariable String id) {
        try {
            afectadoService.eliminarAfectado(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


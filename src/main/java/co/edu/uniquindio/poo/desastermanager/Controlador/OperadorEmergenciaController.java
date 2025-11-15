package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.OperadorEmergencia;
import co.edu.uniquindio.poo.desastermanager.Servicios.OperadorEmergenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//cambiar por lista propia
import java.util.List;

@RestController
@RequestMapping("/operadores")
public class OperadorEmergenciaController {

    private final OperadorEmergenciaService operadorService;

    public OperadorEmergenciaController(OperadorEmergenciaService operadorService) {
        this.operadorService = operadorService;
    }

    // Crear un nuevo operador
    @PostMapping
    public ResponseEntity<OperadorEmergencia> crearOperador(@RequestBody OperadorEmergencia operador) {
        try {
            OperadorEmergencia opGuardado = operadorService.crearOperador(operador);
            return new ResponseEntity<>(opGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar todos los operadores
    @GetMapping
    public ResponseEntity<List<OperadorEmergencia>> listarOperadores() {
        try {
            ListaSimpleEnlazada<OperadorEmergencia> lista = operadorService.listarOperadores();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un operador por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOperador(@PathVariable String id) {
        try {
            operadorService.eliminarOperador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


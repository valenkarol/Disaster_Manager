package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Servicios.UbicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @PostMapping
    public ResponseEntity<Ubicacion> crearUbicacion(@RequestBody Ubicacion ubicacion) {
        return new ResponseEntity<>(ubicacionService.crearUbicacion(ubicacion), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ubicacion>> listarUbicaciones() {
        return new ResponseEntity<>(ubicacionService.listarUbicaciones(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUbicacion(@PathVariable String id) {
        ubicacionService.eliminarUbicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

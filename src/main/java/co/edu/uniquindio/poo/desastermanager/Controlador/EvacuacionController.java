package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import co.edu.uniquindio.poo.desastermanager.Servicios.EvacuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/evacuaciones")
public class EvacuacionController {

    @Autowired
    private EvacuacionService evacuacionService;

    @PostMapping
    public ResponseEntity<Evacuacion> crearEvacuacion(@RequestBody Evacuacion evacuacion) {
        try {
            Evacuacion nueva = evacuacionService.crearEvacuacion(evacuacion);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET - listar todos   CAMBIAR A LISTA PROPIA
    @GetMapping
    public ResponseEntity<List<Evacuacion>> listarEvacuaciones() {
        return new ResponseEntity<>(evacuacionService.listarEvacuaciones(), HttpStatus.OK);
    }

    // GET - por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evacuacion> obtenerEvacuacionPorId(@PathVariable String id) {
        Optional<Evacuacion> evacuacion = evacuacionService.obtenerEvacuacionPorId(id);
        return evacuacion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evacuacion> actualizarEvacuacion(@PathVariable String id, @RequestBody Evacuacion evacuacionActualizada) {
        Evacuacion actualizada = evacuacionService.actualizarEvacuacion(id, evacuacionActualizada);
        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvacuacion(@PathVariable String id) {
        try {
            evacuacionService.eliminarEvacuacion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
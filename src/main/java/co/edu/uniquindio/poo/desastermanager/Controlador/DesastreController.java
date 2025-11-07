package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Desastre;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Servicios.DesastreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/desastres")
public class DesastreController {
    @Autowired
    private DesastreService desastreService;
    @PostMapping
    public ResponseEntity<Desastre> crearDesastre(@RequestBody Desastre desastre) {
        try {
            Desastre nuevo = desastreService.crearDesastre(desastre);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - todos LISTA PROPIA
    @GetMapping
    public ResponseEntity<ListaSimpleEnlazada<Desastre>> listarDesastres() {
        return new ResponseEntity<>(desastreService.listarDesastres(), HttpStatus.OK);
    }

    // READ - por ID MAPA PRIPIO
    @GetMapping("/{id}")
    public ResponseEntity<Desastre> obtenerDesastrePorId(@PathVariable String id) {
        Optional<Desastre> desastre = desastreService.obtenerDesastrePorId(id);
        return desastre.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Desastre> actualizarDesastre(@PathVariable String id, @RequestBody Desastre desastreActualizado) {
        Desastre actualizado = desastreService.actualizarDesastre(id, desastreActualizado);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDesastre(@PathVariable String id) {
        try {
            desastreService.eliminarDesastre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

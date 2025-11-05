package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Informe;
import co.edu.uniquindio.poo.desastermanager.Servicios.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/informes")
public class InformeController {
    @Autowired
    private InformeService informeService;

    // GET - listar todos CAMBIAR A LISTA PROPIA
    @GetMapping
    public ResponseEntity<List<Informe>> listarInformes() {
        return new ResponseEntity<>(informeService.listarInformes(), HttpStatus.OK);
    }

    // GET - uno por ID REALIZAR MAPA PROPIO TAL VEZ
    @GetMapping("/{id}")
    public ResponseEntity<Informe> obtenerInformePorId(@PathVariable String id) {
        Optional<Informe> informe = informeService.obtenerInformePorId(id);
        return informe.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Informe> crearInforme() {
        try {
            Informe nuevo = informeService.crearInforme();
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Informe> actualizarInforme(@PathVariable String id, @RequestBody Informe informeActualizado) {
        Informe actualizado = informeService.actualizarInforme(id, informeActualizado);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInforme(@PathVariable String id) {
        try {
            informeService.eliminarInforme(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

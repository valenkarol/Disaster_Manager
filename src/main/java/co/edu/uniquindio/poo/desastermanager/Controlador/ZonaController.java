package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//cambiar por lista propia
import java.util.List;

@RestController
@RequestMapping("/zonas")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @PostMapping
    public ResponseEntity<Zona> crearZona(@RequestBody Zona zona) {
        return new ResponseEntity<>(zonaService.crearZona(zona), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListaSimpleEnlazada<Zona>> listarZonas() {
        return new ResponseEntity<>(zonaService.listarZonas(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarZona(@PathVariable String id) {
        zonaService.eliminarZona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

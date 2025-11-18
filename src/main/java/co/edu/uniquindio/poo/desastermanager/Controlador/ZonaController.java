package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/zonas")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @PostMapping
    public ResponseEntity<Zona> crearZona(@RequestBody Zona zona) {
        try {
            Zona nuevaZona = zonaService.crearZona(zona);
            return new ResponseEntity<>(nuevaZona, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarZonas() {
        try {
            ListaSimpleEnlazada<Zona> listaPropia = zonaService.listarZonas();

            // Convertir a List estándar para JSON
            List<Zona> listaStandard = new ArrayList<>();
            for (Zona zona : listaPropia) {
                listaStandard.add(zona);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("lista", listaStandard);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarZona(@PathVariable String id) {
        zonaService.eliminarZona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.MapaSimple;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.RutaService;
import co.edu.uniquindio.poo.desastermanager.Servicios.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/rutas")
public class RutaController {

    private final RutaService rutaService;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @PostMapping
    public ResponseEntity<Ruta> crearRuta(@RequestBody Ruta ruta) {
        return new ResponseEntity<>(rutaService.crearRuta(ruta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListaSimpleEnlazada<Ruta>> listarRutas() {
        return new ResponseEntity<>(rutaService.listarRutas(), HttpStatus.OK);
    }

    @PostMapping("/calcular")
    public ResponseEntity<Map<String, Object>> calcularRuta(@RequestBody Map<String, String> req) {
        Ubicacion origen = ubicacionRepository.findById(req.get("origen")).orElse(null);
        Ubicacion destino = ubicacionRepository.findById(req.get("destino")).orElse(null);

        if (origen == null || destino == null) return ResponseEntity.badRequest().build();

        // Usar tu lógica Dijkstra
        double distancia = rutaService.rutaMasCorta(origen, destino);

        // Calcular tiempo realista
        double velocidadKmH = 40; // puedes ajustar
        double tiempoMinutos = (distancia / velocidadKmH) * 60;
        tiempoMinutos = Math.round(tiempoMinutos * 10) / 10.0; // redondeo a 1 decimal

        // Usar MapaSimple para la respuesta
        Map<String, Object> result = new HashMap<>();
        result.put("distancia", distancia);
        result.put("tiempo", tiempoMinutos); // ejemplo de tiempo
        result.put("estado", "Ruta Disponible");

        return ResponseEntity.ok(result);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable String id) {
        rutaService.eliminarRuta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


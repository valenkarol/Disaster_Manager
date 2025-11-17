package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Servicios.ArbolDistribucionService;
import co.edu.uniquindio.poo.desastermanager.Servicios.RecursoService;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/arbol")
public class ArbolDistribucionController {

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private final ArbolDistribucionService arbolService;

    public ArbolDistribucionController(ArbolDistribucionService service, ArbolDistribucionService arbolService) {
        this.arbolService = arbolService;
    }

    @PostMapping("/asignar")
    public ResponseEntity<String> asignarRecurso(
            @RequestParam String zonaId,
            @RequestParam String recursoId) {

        try {
            Zona zona = zonaService.buscarZonaPorId(zonaId);
            if (zona == null) {
                return new ResponseEntity<>("Zona no encontrada", HttpStatus.NOT_FOUND);
            }

            Optional<Recurso> recursoOpt = recursoService.obtenerRecursoPorId(recursoId);
            if (!recursoOpt.isPresent()) {
                return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
            }
            Recurso recurso = recursoOpt.get();

            arbolService.asignarRecursoAZona(zona, recurso);
            return new ResponseEntity<>("Recurso asignado correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al asignar recurso: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/ver")
    public void ver() {
        arbolService.verDistribucion();
    }
}


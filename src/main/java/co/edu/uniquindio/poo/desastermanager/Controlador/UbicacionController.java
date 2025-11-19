package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.UbicacionMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.MapaSimple;
import co.edu.uniquindio.poo.desastermanager.Modelo.TipoUbicacion;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Servicios.UbicacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<ListaSimpleEnlazada<Ubicacion>> listarUbicaciones() {
        return new ResponseEntity<>(ubicacionService.listarUbicaciones(), HttpStatus.OK);
    }

    @GetMapping("/mapa")
    public ResponseEntity<MapaSimple<String, Ubicacion>> obtenerMapaUbicaciones() {
        return new ResponseEntity<>(ubicacionService.obtenerMapaUbicaciones(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUbicacion(@PathVariable String id) {
        ubicacionService.eliminarUbicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //REVISAR METODO
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> obtenerUbicacion(@PathVariable String id) {
        Ubicacion ubicacion = ubicacionService.obtenerUbicacion(id);
        return ubicacion != null
                ? new ResponseEntity<>(ubicacion, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> actualizarUbicacion(
            @PathVariable String id, @RequestBody Ubicacion ubicacionActualizada) {
        try {
            Ubicacion ubicacion = ubicacionService.actualizarUbicacion(id, ubicacionActualizada);
            return new ResponseEntity<>(ubicacion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verificar/{id}")
    public ResponseEntity<Boolean> verificarUbicacion(@PathVariable String id) {
        boolean existe = ubicacionService.verificarUbicacion(id);
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }
    @PostMapping("/from-dto")
    public ResponseEntity<Ubicacion> crearUbicacionDesdeDTO(@RequestBody UbicacionMapaDTO dto) {
        try {
            Ubicacion u = new Ubicacion();
            u.setId(dto.getId());
            u.setNombre(dto.getNombre());
            u.setDireccion(dto.getDireccion());
            u.setCoordenadas(dto.getCoordenadas());
            u.setPrioridad(dto.getPrioridad() != null ? dto.getPrioridad().toUpperCase() : "LOW");
            u.setNivelRiesgo(dto.getNivelRiesgo());
            u.setRecursos(dto.getRecursos());
            u.setEvacuados(dto.getEvacuados());
            u.setEquipos(dto.getEquipos());

            // Mapear tipoUbicacion (string -> enum)
            try {
                u.setTipoUbicacion(TipoUbicacion.valueOf(dto.getTipoUbicacion().toUpperCase()));
            } catch (Exception e) {
                // Si no se puede mapear, asigna un valor por defecto
                u.setTipoUbicacion(TipoUbicacion.CIUDAD);
            }

            Ubicacion guardada = ubicacionService.crearUbicacion(u);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

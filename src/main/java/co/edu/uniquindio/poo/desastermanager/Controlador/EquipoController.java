package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Servicios.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // GET - listar todos CAMBIAR A LISTA PROPIA
    @GetMapping
    public ResponseEntity<ListaSimpleEnlazada<Equipo>> listarEquipos() {
        return new ResponseEntity<>(equipoService.listarEquipos(), HttpStatus.OK);
    }

    // GET - obtener uno MIRAR SI IMPLEMENTAR MAPA PROPIO
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable String id) {
        Optional<Equipo> equipo = equipoService.obtenerEquipoPorId(id);
        return equipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        try {
            Equipo nuevo = equipoService.guardarEquipo(equipo);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable String id, @RequestBody Equipo equipoActualizado) {
        Equipo actualizado = equipoService.actualizarEquipo(id, equipoActualizado);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable String id) {
        try {
            equipoService.eliminarEquipo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

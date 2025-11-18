package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Servicios.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    // READ - obtener todos- CAMBIAR POR LISTA PROPIA
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarRecursos() {
        try {
            ListaSimpleEnlazada<Recurso> listaPropia = recursoService.listarRecursos();

            List<Recurso> listaStandard = new ArrayList<>();
            for (Recurso recurso : listaPropia) {
                listaStandard.add(recurso);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("lista", listaStandard);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // READ - obtener uno por ID CAMBIAR POR MAP PROPIO
    @GetMapping("/{id}")
    public ResponseEntity<Recurso> obtenerRecursoPorId(@PathVariable String id) {
        Optional<Recurso> recurso = recursoService.obtenerRecursoPorId(id);
        return recurso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Recurso> crearRecurso(@RequestBody Recurso recurso) {
        try {
            Recurso nuevoRecurso = recursoService.guardarRecurso(recurso);
            return new ResponseEntity<>(nuevoRecurso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizarRecurso(@PathVariable String id, @RequestBody Recurso recursoActualizado) {
        Recurso actualizado = recursoService.actualizarRecurso(id, recursoActualizado);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecurso(@PathVariable String id) {
        try {
            recursoService.eliminarRecurso(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<Boolean> verificarDisponibilidad(@RequestParam String tipo) {
        boolean disponible = recursoService.disponibilidadRecursos(tipo);
        return new ResponseEntity<>(disponible, HttpStatus.OK);
    }
}

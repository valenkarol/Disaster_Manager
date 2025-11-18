package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.Informe;
import co.edu.uniquindio.poo.desastermanager.Servicios.InformeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/informes")
public class InformeController {
    @Autowired
    private InformeService informeService;

    // GET - listar todos CAMBIAR A LISTA PROPIA
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarInformes() {
        try {
            ListaSimpleEnlazada<Informe> listaPropia = informeService.listarInformes();

            // Convertir a List estándar para JSON
            List<Informe> listaStandard = new ArrayList<>();
            for (Informe informe : listaPropia) {
                listaStandard.add(informe);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("lista", listaStandard);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PostMapping("/generar")
    public ResponseEntity<String> generarInforme() {
        try {
            Informe informe = informeService.realizarInforme();
            return new ResponseEntity<>(informe.getResumen(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al generar informe: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

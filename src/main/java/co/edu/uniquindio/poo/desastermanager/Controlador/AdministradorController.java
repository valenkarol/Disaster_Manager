package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Administrador;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Servicios.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostMapping
    public ResponseEntity<Administrador> crearAdministrador(@RequestBody Administrador administrador) {
        return new ResponseEntity<>(administradorService.crearAdministrador(administrador), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListaSimpleEnlazada<Administrador>> listarAdministradores() {
        return new ResponseEntity<>(administradorService.listarAdministradores(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable String id) {
        administradorService.eliminarAdministrador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

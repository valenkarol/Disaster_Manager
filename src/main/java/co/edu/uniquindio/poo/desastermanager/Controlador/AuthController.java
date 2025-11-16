package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.Persona;
import co.edu.uniquindio.poo.desastermanager.Servicios.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
//mapa propio no en controller, no lo recibe 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {

        System.out.println("=== PETICIÓN /auth/login RECIBIDA ===");
        System.out.println("JSON recibido: " + data);

        String email = data.get("email");
        String password = data.get("password");

        System.out.println("Email recibido: " + email);
        System.out.println("Password recibido: " + password);

        Persona user = authService.login(email, password);

        System.out.println("Resultado login: " + user);

        if (user == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok(user);
    }
}

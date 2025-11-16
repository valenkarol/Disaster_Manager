package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Administrador;
import co.edu.uniquindio.poo.desastermanager.Modelo.OperadorEmergencia;
import co.edu.uniquindio.poo.desastermanager.Modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdministradorService adminService;

    @Autowired
    private OperadorEmergenciaService operadorService;

    public Persona login(String email, String password) {

        Administrador admin = adminService.login(email, password);
        if (admin != null) return admin;

        OperadorEmergencia operador = operadorService.login(email, password);
        if (operador != null) return operador;

        return null;
    }
}

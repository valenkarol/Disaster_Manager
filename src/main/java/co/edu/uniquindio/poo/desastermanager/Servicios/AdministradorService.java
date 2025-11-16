package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Administrador;
import co.edu.uniquindio.poo.desastermanager.Repositorio.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;


@Service
public class AdministradorService {

    @Autowired
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador login(String email, String password) {
        System.out.println("=== ADMIN login ===");
        System.out.println("Buscando por email: " + email);

        Administrador admin = administradorRepository.findByEmail(email).orElse(null);

        System.out.println("Resultado de findByEmail: " + admin);

        if (admin != null) {
            System.out.println("Password en BD: " + admin.getPassword());
            System.out.println("Password ingresada: " + password);
        }

        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println(">>> LOGIN ADMIN EXITOSO");
            return admin;
        }

        System.out.println(">>> LOGIN ADMIN FALLÓ");
        return null;
    }

    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public ListaSimpleEnlazada<Administrador> listarAdministradores() {
        ListaSimpleEnlazada<Administrador> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Administrador> listaMongo = administradorRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Administrador admin : listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(admin));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
    }


    public void eliminarAdministrador(String id) {
        administradorRepository.deleteById(id);
    }
}

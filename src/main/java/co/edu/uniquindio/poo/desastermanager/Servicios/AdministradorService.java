package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Administrador;
import co.edu.uniquindio.poo.desastermanager.Repositorio.AdministradorRepository;
import org.springframework.stereotype.Service;
//importar lista propia
import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public List<Administrador> listarAdministradores() {
        return administradorRepository.findAll();
    }

    public void eliminarAdministrador(String id) {
        administradorRepository.deleteById(id);
    }
}

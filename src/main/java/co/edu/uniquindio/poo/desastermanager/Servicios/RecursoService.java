package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;
    public Recurso guardarRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }
    public Optional<Recurso> obtenerRecursoPorId(String id) {
        return recursoRepository.findById(id);
    }

    // READ - todos
    public List<Recurso> listarRecursos() {
        return recursoRepository.findAll();
    }
    public Recurso actualizarRecurso(String id, Recurso recursoActualizado) {
        Optional<Recurso> recursoOptional = recursoRepository.findById(id);
        if (recursoOptional.isPresent()) {
            Recurso recurso = recursoOptional.get();
            recurso.setNombre(recursoActualizado.getNombre());
            recurso.setCantidad(recursoActualizado.getCantidad());
            recurso.setTipo(recursoActualizado.getTipo());
            return recursoRepository.save(recurso);
        }
        return null;
    }
    public void eliminarRecurso(String id) {
        recursoRepository.deleteById(id);
    }

    public boolean disponibilidadRecursos(String tipo) {
        return recursoRepository.findAll().stream()
                .anyMatch(r -> r.getTipo().name().equalsIgnoreCase(tipo));
    }

    public void distribuirRecursos(Recurso recurso, int cantidad) {
        if (recurso.getCantidad() >= cantidad) {
            recurso.setCantidad(recurso.getCantidad() - cantidad);
            recursoRepository.save(recurso);
        } else {
            System.out.println("No hay suficientes recursos del tipo " + recurso.getTipo());
        }
    }
}

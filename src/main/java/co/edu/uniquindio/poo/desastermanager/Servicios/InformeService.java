package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Informe;
import co.edu.uniquindio.poo.desastermanager.Repositorio.InformeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InformeService {
    @Autowired
    private InformeRepository informeRepository;

    public Informe crearInforme() {
        Informe informe = new Informe();
        informe.setFecha(LocalDateTime.now());
        return informeRepository.save(informe);
    }

    // READ - todos LISTA PROPIA
    public List<Informe> listarInformes() {
        return informeRepository.findAll();
    }

    // READ - por ID
    public Optional<Informe> obtenerInformePorId(String id) {
        return informeRepository.findById(id);
    }

    public Informe actualizarInforme(String id, Informe informeActualizado) {
        Optional<Informe> informeOptional = informeRepository.findById(id);
        if (informeOptional.isPresent()) {
            Informe informe = informeOptional.get();
            informe.setFecha(informeActualizado.getFecha());
            return informeRepository.save(informe);
        }
        return null;
    }
    public void eliminarInforme(String id) {
        informeRepository.deleteById(id);
    }
}

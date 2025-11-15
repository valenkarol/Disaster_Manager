package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import co.edu.uniquindio.poo.desastermanager.Modelo.Informe;
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
    public ListaSimpleEnlazada<Informe> listarInformes() {
        ListaSimpleEnlazada<Informe> listaPropia = new ListaSimpleEnlazada<>();

        // 1. Mongo devuelve una List normal
        java.util.List<Informe> listaMongo = informeRepository.findAll();

        // 2. Convertimos a nuestra lista
        for (Informe informe: listaMongo) {
            listaPropia.agregarUltimo(new NodoLS<>(informe));
        }

        // 3. Retornamos nuestra estructura
        return listaPropia;
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

    public Informe realizarInforme() {
        Informe informe = new Informe();
        informe.setFecha(LocalDateTime.now());
        return informeRepository.save(informe);
    }
}

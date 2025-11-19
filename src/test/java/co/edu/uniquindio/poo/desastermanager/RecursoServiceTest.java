package co.edu.uniquindio.poo.desastermanager;

import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Modelo.TipoRecurso;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RecursoRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.RecursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecursoServiceTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoService recursoService;

    // ------------------------------
    // guardarRecurso()
    // ------------------------------
    @Test
    void guardarRecurso_debeGuardarYRetornar() {
        Recurso recurso = new Recurso();
        when(recursoRepository.save(recurso)).thenReturn(recurso);

        Recurso result = recursoService.guardarRecurso(recurso);

        assertEquals(recurso, result);
    }

    // ------------------------------
    // obtenerRecursoPorId()
    // ------------------------------
    @Test
    void obtenerRecursoPorId_retornaOptional() {
        Recurso recurso = new Recurso();
        when(recursoRepository.findById("1")).thenReturn(Optional.of(recurso));

        Optional<Recurso> result = recursoService.obtenerRecursoPorId("1");

        assertTrue(result.isPresent());
        assertEquals(recurso, result.get());
    }

    // ------------------------------
    // listarRecursos()
    // ------------------------------
    @Test
    void listarRecursos_convierteListaMongoAListaPropia() {
        Recurso r1 = new Recurso();
        Recurso r2 = new Recurso();

        when(recursoRepository.findAll()).thenReturn(List.of(r1, r2));

        var lista = recursoService.listarRecursos();

        assertEquals(2, lista.tamaño());
    }

    // ------------------------------
    // obtenerMapaRecursos()
    // ------------------------------
    @Test
    void obtenerMapaRecursos_creaMapaConIds() {
        Recurso r1 = new Recurso();
        r1.setId("A");
        Recurso r2 = new Recurso();
        r2.setId("B");

        when(recursoRepository.findAll()).thenReturn(List.of(r1, r2));

        var mapa = recursoService.obtenerMapaRecursos();

        assertEquals(r1, mapa.get("A"));
        assertEquals(r2, mapa.get("B"));
    }


}
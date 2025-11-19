package co.edu.uniquindio.poo.desastermanager;

import co.edu.uniquindio.poo.desastermanager.Modelo.Desastre;
import co.edu.uniquindio.poo.desastermanager.Repositorio.DesastreRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.DesastreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DesastreServiceTest {

    @Mock
    private DesastreRepository desastreRepository;

    @InjectMocks
    private DesastreService desastreService;

    // ------------------------------
    // crearDesastre()
    // ------------------------------
    @Test
    void crearDesastre_debeGuardarYRetornar() {
        Desastre d = new Desastre();
        when(desastreRepository.save(d)).thenReturn(d);

        Desastre result = desastreService.crearDesastre(d);

        assertEquals(d, result);
    }

    // ------------------------------
    // listarDesastres()
    // ------------------------------
    @Test
    void listarDesastres_convierteListaMongoAListaPropia() {
        Desastre d1 = new Desastre();
        Desastre d2 = new Desastre();

        when(desastreRepository.findAll()).thenReturn(List.of(d1, d2));

        var lista = desastreService.listarDesastres();

        assertEquals(2, lista.tamaño());
    }

    // ------------------------------
    // obtenerDesastrePorId()
    // ------------------------------
    @Test
    void obtenerDesastrePorId_retornaOptionalCorrecto() {
        Desastre d = new Desastre();
        when(desastreRepository.findById("1")).thenReturn(Optional.of(d));

        Optional<Desastre> result = desastreService.obtenerDesastrePorId("1");

        assertTrue(result.isPresent());
        assertEquals(d, result.get());
    }


    @Test
    void actualizarDesastre_devuelveNullSiNoExiste() {
        when(desastreRepository.findById("X")).thenReturn(Optional.empty());

        assertNull(desastreService.actualizarDesastre("X", new Desastre()));
    }

    // ------------------------------
    // eliminarDesastre()
    // ------------------------------
    @Test
    void eliminarDesastre_invocaDeleteCorrectamente() {
        desastreService.eliminarDesastre("1");
    }
}


package co.edu.uniquindio.poo.desastermanager;

import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZonaServiceTest {
    @Mock
    private ZonaRepository zonaRepository;

    @InjectMocks
    private ZonaService zonaService;

    private Zona zona;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zona = new Zona();
        zona.setId("Z1");
        zona.setNombreZona("Zona A");
    }


    @Test
    void testCrearZona() {
        when(zonaRepository.save(zona)).thenReturn(zona);

        Zona resultado = zonaService.crearZona(zona);

        assertNotNull(resultado);
        assertEquals("Z1", resultado.getId());
        verify(zonaRepository, times(1)).save(zona);
    }



    @Test
    void testBuscarZonaPorId_encontrada() {
        when(zonaRepository.findAll()).thenReturn(List.of(zona));

        Zona resultado = zonaService.buscarZonaPorId("Z1");

        assertNotNull(resultado);
        assertEquals("Z1", resultado.getId());
    }

    @Test
    void testBuscarZonaPorId_noEncontrada() {
        when(zonaRepository.findAll()).thenReturn(List.of(zona));

        Zona resultado = zonaService.buscarZonaPorId("Z999");

        assertNull(resultado);
    }

    @Test
    void testEliminarZona() {
        doNothing().when(zonaRepository).deleteById("Z1");

        zonaService.eliminarZona("Z1");

        verify(zonaRepository, times(1)).deleteById("Z1");
    }
}


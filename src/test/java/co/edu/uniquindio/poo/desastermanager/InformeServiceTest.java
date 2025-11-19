package co.edu.uniquindio.poo.desastermanager;

import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Modelo.Informe;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EquipoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.InformeRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RecursoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.InformeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InformeServiceTest {

    @Mock
    private InformeRepository informeRepository;

    @Mock
    private ZonaRepository zonaRepository;

    @Mock
    private RecursoRepository recursoRepository;

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private InformeService informeService;

    private Informe informe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        informe = new Informe();
        informe.setId("I1");
        informe.setFecha(LocalDateTime.now());
    }

    @Test
    void testCrearInforme() {
        when(informeRepository.save(any(Informe.class))).thenReturn(informe);

        Informe resultado = informeService.crearInforme();

        assertNotNull(resultado);
        assertEquals(informe.getId(), resultado.getId());
        verify(informeRepository, times(1)).save(any(Informe.class));
    }


    @Test
    void testObtenerInformePorId() {
        when(informeRepository.findById("I1")).thenReturn(Optional.of(informe));

        Optional<Informe> resultado = informeService.obtenerInformePorId("I1");

        assertTrue(resultado.isPresent());
        assertEquals(informe, resultado.get());
        verify(informeRepository, times(1)).findById("I1");
    }

    @Test
    void testActualizarInforme() {
        Informe actualizado = new Informe();
        actualizado.setFecha(LocalDateTime.now().plusDays(1));

        when(informeRepository.findById("I1")).thenReturn(Optional.of(informe));
        when(informeRepository.save(any(Informe.class))).thenReturn(actualizado);

        Informe resultado = informeService.actualizarInforme("I1", actualizado);

        assertNotNull(resultado);
        assertEquals(actualizado.getFecha(), resultado.getFecha());
        verify(informeRepository, times(1)).findById("I1");
        verify(informeRepository, times(1)).save(informe);
    }

    @Test
    void testEliminarInforme() {
        doNothing().when(informeRepository).deleteById("I1");

        informeService.eliminarInforme("I1");

        verify(informeRepository, times(1)).deleteById("I1");
    }

    @Test
    void testRealizarInforme() {
        Zona zona = new Zona();
        zona.setId("Z1");
        zona.setNombreZona("Zona 1");
        zona.setNivelRiesgo(5);

        Recurso recurso = new Recurso();
        recurso.setZonaAsignada("Z1");
        recurso.setNombre("Camion");
        recurso.setCantidad(2);


        Equipo equipo = new Equipo();
        equipo.setZonaAsignada("Z1");
        equipo.setCantidadMiembros(4);

        when(informeRepository.save(any(Informe.class))).thenReturn(informe);
        when(zonaRepository.findAll()).thenReturn(List.of(zona));
        when(recursoRepository.findAll()).thenReturn(List.of(recurso));
        when(equipoRepository.findAll()).thenReturn(List.of(equipo));

        Informe resultado = informeService.realizarInforme();

        assertNotNull(resultado);
        assertNotNull(resultado.getResumen());
        assertTrue(resultado.getResumen().contains("Zona: Zona 1"));
        assertTrue(resultado.getResumen().contains("Recursos:"));
        assertTrue(resultado.getResumen().contains("Equipos:"));
        verify(informeRepository, times(2)).save(any(Informe.class)); // primera y segunda vez
        verify(zonaRepository, times(1)).findAll();
        verify(recursoRepository, times(1)).findAll();
        verify(equipoRepository, times(1)).findAll();
    }
}

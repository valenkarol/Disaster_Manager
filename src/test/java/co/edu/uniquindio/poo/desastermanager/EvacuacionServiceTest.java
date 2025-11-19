package co.edu.uniquindio.poo.desastermanager;

import co.edu.uniquindio.poo.desastermanager.Modelo.Evacuacion;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EvacuacionRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.EvacuacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvacuacionServiceTest {

    @Mock
    private EvacuacionRepository evacuacionRepository;

    @Mock
    private ZonaRepository zonaRepository;

    @InjectMocks
    private EvacuacionService evacuacionService;

    // ------------------------------
    // crearEvacuacion()
    // ------------------------------
    @Test
    void crearEvacuacion_debeLanzarExcepcionSiZonaEsNull() {
        Evacuacion ev = new Evacuacion();
        ev.setZona(null);

        assertThrows(RuntimeException.class, () -> evacuacionService.crearEvacuacion(ev));
    }

    @Test
    void crearEvacuacion_debeLanzarExcepcionSiIdZonaEsNull() {
        Evacuacion ev = new Evacuacion();
        ev.setZona(new Zona());

        assertThrows(RuntimeException.class, () -> evacuacionService.crearEvacuacion(ev));
    }
    
    // ------------------------------
    // obtenerEvacuacionPorId()
    // ------------------------------
    @Test
    void obtenerEvacuacionPorId_retornaOptionalCorrecto() {
        Evacuacion ev = new Evacuacion();
        when(evacuacionRepository.findById("1")).thenReturn(Optional.of(ev));

        Optional<Evacuacion> result = evacuacionService.obtenerEvacuacionPorId("1");

        assertTrue(result.isPresent());
        assertEquals(ev, result.get());
    }

    // ------------------------------
    // actualizarEvacuacion()
    // ------------------------------
    @Test
    void actualizarEvacuacion_actualizaYGuarda() {
        Evacuacion existente = new Evacuacion();
        existente.setPrioridad(1);
        existente.setNumeroAfectados(5);

        Evacuacion actualizada = new Evacuacion();
        actualizada.setPrioridad(3);
        actualizada.setNumeroAfectados(10);

        when(evacuacionRepository.findById("1")).thenReturn(Optional.of(existente));
        when(evacuacionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Evacuacion resultado = evacuacionService.actualizarEvacuacion("1", actualizada);

        assertEquals(3, resultado.getPrioridad());
        assertEquals(10, resultado.getNumeroAfectados());
    }

    @Test
    void actualizarEvacuacion_retornaNullSiNoExiste() {
        when(evacuacionRepository.findById("X")).thenReturn(Optional.empty());

        assertNull(evacuacionService.actualizarEvacuacion("X", new Evacuacion()));
    }


    // ------------------------------
    // esProcesada()
    // ------------------------------
    @Test
    void esProcesada_trueSiNoEstaEnLaCola() {
        Evacuacion ev = new Evacuacion();

        boolean result = evacuacionService.esProcesada(ev);

        assertTrue(result); // nunca fue insertado
    }


}


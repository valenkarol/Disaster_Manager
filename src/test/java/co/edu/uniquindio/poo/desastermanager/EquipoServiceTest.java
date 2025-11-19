package co.edu.uniquindio.poo.desastermanager;


import co.edu.uniquindio.poo.desastermanager.Modelo.Equipo;
import co.edu.uniquindio.poo.desastermanager.Modelo.TipoEquipoRescate;
import co.edu.uniquindio.poo.desastermanager.Repositorio.EquipoRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.ZonaRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.EquipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {
    @Mock
    private EquipoRepository equipoRepository;
    @Mock private ZonaRepository zonaRepository;
    @InjectMocks
    private EquipoService equipoService;
    private Equipo equipo;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        equipo = new Equipo("1", TipoEquipoRescate.BOMBERO, 5, null); }
    @Test
    void testGuardarEquipo() {
        when(equipoRepository.save(equipo)).thenReturn(equipo);
        Equipo resultado = equipoService.guardarEquipo(equipo);
        assertNotNull(resultado);
        assertEquals(equipo.getId(), resultado.getId());
        verify(equipoRepository, times(1)).save(equipo); }
    @Test void testObtenerEquipoPorId() {
        when(equipoRepository.findById("1")).thenReturn(Optional.of(equipo));
        Optional<Equipo> resultado = equipoService.obtenerEquipoPorId("1");
        assertTrue(resultado.isPresent()); assertEquals(equipo, resultado.get());
        verify(equipoRepository, times(1)).findById("1"); }
    @Test void testActualizarEquipo() {
        Equipo actualizado = new Equipo(null, TipoEquipoRescate.MEDICO, 10, null);
        when(equipoRepository.findById("1")).thenReturn(Optional.of(equipo));
        when(equipoRepository.save(any(Equipo.class))).thenReturn(actualizado);
        Equipo resultado = equipoService.actualizarEquipo("1", actualizado);
        assertNotNull(resultado);
        assertEquals(actualizado.getTipo(), resultado.getTipo());
        assertEquals(actualizado.getCantidadMiembros(), resultado.getCantidadMiembros());
        verify(equipoRepository, times(1)).findById("1");
        verify(equipoRepository, times(1)).save(equipo); }
    @Test void testEliminarEquipo() {
        doNothing().when(equipoRepository).deleteById("1");
        equipoService.eliminarEquipo("1");
        verify(equipoRepository, times(1)).deleteById("1"); }

    @Test void testAsignarEquipoAZona_EquipoNoExiste() {
        when(equipoRepository.findById("1")).thenReturn(Optional.empty());
        boolean resultado = equipoService.asignarEquipoAZona("Z1", "1", 8);
        assertFalse(resultado);
        verify(equipoRepository, times(1)).findById("1");
        verifyNoMoreInteractions(zonaRepository); }
    @Test void testAsignarEquipoAZona_ZonaNoExiste() {
        when(equipoRepository.findById("1")).thenReturn(Optional.of(equipo));
        when(zonaRepository.findById("Z1")).thenReturn(Optional.empty());
        boolean resultado = equipoService.asignarEquipoAZona("Z1", "1", 8);
        assertFalse(resultado);
        verify(equipoRepository, times(1)).findById("1");
        verify(zonaRepository, times(1)).findById("Z1");
        verify(equipoRepository, never()).save(any()); }
}

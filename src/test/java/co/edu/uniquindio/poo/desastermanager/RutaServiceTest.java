package co.edu.uniquindio.poo.desastermanager;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Repositorio.RutaRepository;
import co.edu.uniquindio.poo.desastermanager.Repositorio.UbicacionRepository;
import co.edu.uniquindio.poo.desastermanager.Servicios.RutaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RutaServiceTest {

    @Mock
    private RutaRepository rutaRepository;

    @Mock
    private UbicacionRepository ubicacionRepository;

    @InjectMocks
    private RutaService rutaService;

    // ------------------------------
    // crearRuta()
    // ------------------------------
    @Test
    void crearRuta_debeGuardarYRetornar() {
        Ruta r = new Ruta();
        when(rutaRepository.save(r)).thenReturn(r);

        Ruta result = rutaService.crearRuta(r);

        assertEquals(r, result);
    }

    // ------------------------------
    // listarRutas()
    // ------------------------------
    @Test
    void listarRutas_convierteListaMongoAListaPropia() {
        Ruta r1 = new Ruta();
        Ruta r2 = new Ruta();

        when(rutaRepository.findAll()).thenReturn(List.of(r1, r2));

        var lista = rutaService.listarRutas();

        assertEquals(2, lista.tamaño());
    }


    // ------------------------------
    // eliminarRuta()
    // ------------------------------
    @Test
    void eliminarRuta_invocaDelete() {
        rutaService.eliminarRuta("1");
        verify(rutaRepository).deleteById("1");
    }


}


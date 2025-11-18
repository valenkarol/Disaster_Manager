package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.UbicacionMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.ZonaMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.RutaMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Servicios.RutaService;
import co.edu.uniquindio.poo.desastermanager.Servicios.UbicacionService;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mapa")
@CrossOrigin(origins = "*")
public class MapaDTOController {

    private final RutaService rutaService;
    private final ZonaService zonaService;
    private final UbicacionService ubicacionService;

    public MapaDTOController(RutaService rutaService, ZonaService zonaService, UbicacionService ubicacionService) {
        this.rutaService = rutaService;
        this.zonaService = zonaService;
        this.ubicacionService = ubicacionService;
    }

    @GetMapping("/rutas")
    public List<RutaMapaDTO> obtenerRutasMapa() {
        ListaSimpleEnlazada<Ruta> rutas = rutaService.listarRutas();
        List<RutaMapaDTO> listaMapa = new ArrayList<>();

        NodoLS<Ruta> nodo = rutas.getPrimero();
        while (nodo != null) {
            listaMapa.add(new RutaMapaDTO(nodo.getDato()));
            nodo = nodo.getProximo();
        }

        return listaMapa;
    }
//Conierte lista propia a lista estandar de dto para que el front la pueda leer
    @GetMapping("/zonas")
    public List<ZonaMapaDTO> obtenerZonasMapa() {
        return zonaService.listarZonas().toList().stream()
                .map(z -> new ZonaMapaDTO(z.getId(), z.getNombreZona(), z.getNivelRiesgo()))
                .collect(Collectors.toList());
    }

    @GetMapping("/ubicaciones")
    public List<UbicacionMapaDTO> obtenerUbicacionesMapa() {
        return ubicacionService.listarUbicaciones().toList().stream()
                .map(u -> new UbicacionMapaDTO(u.getId(), u.getCoordenadas(), u.getTipoUbicacion().name()))
                .collect(Collectors.toList());
    }
}
package co.edu.uniquindio.poo.desastermanager.Controlador;

import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.RutaMapaResponseDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.UbicacionMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.ZonaMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ListaSimpleEnlazada;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoLS;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ruta;
import co.edu.uniquindio.poo.desastermanager.Modelo.DTO.RutaMapaDTO;
import co.edu.uniquindio.poo.desastermanager.Modelo.Ubicacion;
import co.edu.uniquindio.poo.desastermanager.Servicios.RutaService;
import co.edu.uniquindio.poo.desastermanager.Servicios.UbicacionService;
import co.edu.uniquindio.poo.desastermanager.Servicios.ZonaService;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
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
    public List<RutaMapaResponseDTO> obtenerRutasMapa() {

        ListaSimpleEnlazada<Ruta> rutas = rutaService.listarRutas();
        List<RutaMapaResponseDTO> lista = new ArrayList<>();

        NodoLS<Ruta> nodo = rutas.getPrimero();
        while (nodo != null) {
            Ruta r = nodo.getDato();

            String origenCoords = null;
            String destinoCoords = null;

            // 1️⃣ Intentar obtener coords desde el objeto (lo más común)
            if (r.getOrigen() != null) origenCoords = r.getOrigen().getCoordenadas();
            if (r.getDestino() != null) destinoCoords = r.getDestino().getCoordenadas();

            // 2️⃣ Si faltan coordenadas, se buscan en la BD por ID
            if ((origenCoords == null || origenCoords.isEmpty()) && r.getOrigen() != null) {
                Ubicacion u = ubicacionService.obtenerUbicacion(r.getOrigen().getId());
                if (u != null) origenCoords = u.getCoordenadas();
            }
            if ((destinoCoords == null || destinoCoords.isEmpty()) && r.getDestino() != null) {
                Ubicacion u = ubicacionService.obtenerUbicacion(r.getDestino().getId());
                if (u != null) destinoCoords = u.getCoordenadas();
            }

            // 3️⃣ Si aun así falta alguna coordenada → NO se manda la ruta
            if (origenCoords == null || origenCoords.isEmpty() ||
                    destinoCoords == null || destinoCoords.isEmpty()) {

                System.out.println("⚠ Ruta omitida (sin coordenadas): " + r.getId());
                nodo = nodo.getProximo();
                continue;
            }

            // 4️⃣ Agregar ruta válida
            lista.add(new RutaMapaResponseDTO(
                    r.getId(),
                    origenCoords,
                    destinoCoords,
                    r.getDistancia(),
                    r.getEstadoRuta()
            ));

            nodo = nodo.getProximo();
        }

        return lista;
    }

    //Conierte lista propia a lista estandar de dto para que el front la pueda leer
    @GetMapping("/zonas")
    public List<ZonaMapaDTO> obtenerZonasMapa() {
        return zonaService.listarZonas().toList().stream()
                .map(z -> new ZonaMapaDTO(
                        z.getId(),
                        z.getNombreZona(),
                        z.getNivelRiesgo(),
                        z.getCoordenadas()   // <-- asegúrate de que Zona tenga coordenadas en la BD
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/ubicaciones")
    public List<UbicacionMapaDTO> obtenerUbicacionesMapa() {

        ListaSimpleEnlazada<Ubicacion> lista = ubicacionService.listarUbicaciones();
        List<UbicacionMapaDTO> respuesta = new ArrayList<>();

        NodoLS<Ubicacion> nodo = lista.getPrimero();
        while (nodo != null) {
            Ubicacion u = nodo.getDato();

            respuesta.add(new UbicacionMapaDTO(
                    u.getId(),
                    u.getNombre(),
                    u.getDireccion(),
                    u.getCoordenadas(),
                    u.getTipoUbicacion().name(),
                    u.getPrioridad() != null ? u.getPrioridad().toLowerCase() : "low", // normalizar a lowercase
                    u.getNivelRiesgo(),
                    u.getRecursos(),
                    u.getEvacuados(),
                    u.getEquipos()
            ));

            nodo = nodo.getProximo();
        }

        return respuesta;
    }
}

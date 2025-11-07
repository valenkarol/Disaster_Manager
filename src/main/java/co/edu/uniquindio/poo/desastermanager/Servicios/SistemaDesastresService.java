package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SistemaDesastresService {

    @Autowired
    private RecursoService recursoService;
    @Autowired
    private RutaService rutaService;
    @Autowired
    private EvacuacionService evacuacionService;
    @Autowired
    private DesastreService desastreService;

    //Metodos con mas de una clases


    public Ruta definirRuta(Ubicacion origen, Ubicacion destino) {
        double distancia = calcularDistancia(origen, destino);

        Ruta ruta = new Ruta();
        ruta.setOrigen(origen);
        ruta.setDestino(destino);
        ruta.setDistancia(distancia);
        ruta.setEstadoRuta("Activa");

        return rutaService.crearRuta(ruta);
    }


    public void gestionEvacuacion(String idDesastre, Evacuacion evacuacion) {
        Desastre desastre = desastreService.obtenerDesastrePorId(idDesastre).orElse(null);
        if (desastre != null) {
            evacuacionService.crearEvacuacion(evacuacion);
            System.out.println("Evacuación gestionada para desastre con ID " + idDesastre);
        } else {
            System.out.println("No se encontró el desastre con ID: " + idDesastre);
        }
    }

    public void distribucionRecursos(Zona zona, Recurso recurso, int cantidad) {
        recursoService.distribuirRecursos(recurso, cantidad);
        System.out.println("Distribuidos " + cantidad + " " + recurso.getNombre() + " en la zona " + zona.getNombreZona());
    }

    //crud ¿?
    public void crear() {
        System.out.println("Inicializando el sistema de desastres...");
    }

    public void leer() {
        System.out.println("Leyendo estado del sistema de desastres...");
    }

    public void actualizar() {
        System.out.println("Actualizando configuraciones del sistema...");
    }

    public void eliminar() {
        System.out.println("Cerrando sistema de gestión de desastres...");
    }

}

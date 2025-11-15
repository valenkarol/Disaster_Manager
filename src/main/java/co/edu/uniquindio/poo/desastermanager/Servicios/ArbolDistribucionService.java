package co.edu.uniquindio.poo.desastermanager.Servicios;

import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.ArbolDistribucion;
import co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias.NodoArbol;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import org.springframework.stereotype.Service;

@Service
public class ArbolDistribucionService {

    // Aquí va tu estructura propia (NO el service)
    private final ArbolDistribucion arbol = new ArbolDistribucion();

    public void asignarRecursoAZona(Zona zona, Recurso recurso) {
        arbol.insertar(zona, recurso);
    }

    public void verDistribucion() {
        arbol.recorrerInorden();
    }

    public NodoArbol buscarZona(String idZona) {
        return arbol.buscar(idZona);
    }
}

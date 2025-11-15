package co.edu.uniquindio.poo.desastermanager.Modelo.EstructurasPropias;
import co.edu.uniquindio.poo.desastermanager.Modelo.Zona;
import co.edu.uniquindio.poo.desastermanager.Modelo.Recurso;
import lombok.Data;

@Data
public class NodoArbol {

    private Zona zona;
    private Recurso recursoAsignado;
    private NodoArbol izquierda;
    private NodoArbol derecha;

    public NodoArbol(Zona zona, Recurso recursoAsignado) {
        this.zona = zona;
        this.recursoAsignado = recursoAsignado;
        this.izquierda = null;
        this.derecha = null;
    }
}


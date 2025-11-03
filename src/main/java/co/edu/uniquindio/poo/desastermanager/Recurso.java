package co.edu.uniquindio.poo.desastermanager;

public class Recurso {
    private String id;
    private String nombre;
    private int cantidad;
    private TipoRecurso tipo;

    public Recurso() {
    }

    public Recurso(String id, String nombre, int cantidad, TipoRecurso tipo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public void agregarRecurso(Recurso recurso) {
        this.cantidad += recurso.getCantidad();
    }

    public int calcularTotalRecursos() {
        return this.cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }
}


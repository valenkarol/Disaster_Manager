package co.edu.uniquindio.poo.desastermanager.Modelo;


public class EstadisticaZonaDTO {

    private String zonaId;
    private String nombreZona;
    private int nivelRiesgo;
    private int totalEvacuados;
    private int totalPendientes;
    private int totalRecursos;

    public EstadisticaZonaDTO(String zonaId, String nombreZona, int nivelRiesgo,
                              int totalEvacuados, int totalPendientes, int totalRecursos) {
        this.zonaId = zonaId;
        this.nombreZona = nombreZona;
        this.nivelRiesgo = nivelRiesgo;
        this.totalEvacuados = totalEvacuados;
        this.totalPendientes = totalPendientes;
        this.totalRecursos = totalRecursos;
    }

    public String getZonaId() {
        return zonaId;
    }

    public void setZonaId(String zonaId) {
        this.zonaId = zonaId;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public int getTotalEvacuados() {
        return totalEvacuados;
    }

    public void setTotalEvacuados(int totalEvacuados) {
        this.totalEvacuados = totalEvacuados;
    }

    public int getTotalPendientes() {
        return totalPendientes;
    }

    public void setTotalPendientes(int totalPendientes) {
        this.totalPendientes = totalPendientes;
    }

    public int getTotalRecursos() {
        return totalRecursos;
    }

    public void setTotalRecursos(int totalRecursos) {
        this.totalRecursos = totalRecursos;
    }
}

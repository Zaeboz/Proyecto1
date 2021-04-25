package modelo;

public class Tarea {

    private String descripcion;
    private boolean esObligatoria;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(double tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    private double tiempoDuracion;
    public Tarea(String descripcion, boolean esObligatoria, double tiempoDuracion) {
        this.descripcion = descripcion;
        this.esObligatoria = esObligatoria;
        this.tiempoDuracion = tiempoDuracion;
    }


}

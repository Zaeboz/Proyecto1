package modelo;

import java.io.Serializable;

public class Tarea implements Serializable{

    private static final long serialVersionUID = 1L;

    private String descripcion;
    private boolean esObligatoria;
    private double tiempoDuracion;
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


    public Tarea(String descripcion, boolean esObligatoria, double tiempoDuracion) {
        this.descripcion = descripcion;
        this.esObligatoria = esObligatoria;
        this.tiempoDuracion = tiempoDuracion;
    }

    public Tarea()
    {

    }

}

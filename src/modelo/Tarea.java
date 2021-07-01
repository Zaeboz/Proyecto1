package modelo;

import java.io.Serializable;

public class Tarea implements Cloneable, Serializable{

    private static final long serialVersionUID = 1L;

    private String nombre = "";
    private String nombreActividad = "";
    private String descripcion = "";
    private boolean obligatoria = false;
    private double tiempoMinimo = 0;
    private double tiempoMaximo = 0;

    public Tarea()
    {

    }

    public Tarea(String nombreActividad, String nombre, String descripcion, boolean obligatoria, double tiempoMinimo, double tiempoMaximo) {
        this.nombre = nombre;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.tiempoMaximo = tiempoMaximo;
        this.tiempoMinimo = tiempoMinimo;
    }

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public double getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

    public double getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
}
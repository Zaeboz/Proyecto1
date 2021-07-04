package modelo;

import java.io.Serializable;

public class Tarea implements Cloneable, Serializable{

    private static final long serialVersionUID = 1L;

    private String nombre = "";
    private String nombreActividad = "";
    private String descripcion = "";
    private boolean esOpcional = false;
    private double tiempoDuracion = 0;


    public Tarea()
    {

    }

    public Object clone( ) throws CloneNotSupportedException{
        return super.clone();
    }


    public Tarea(String nombreActividad, String nombre, String descripcion, boolean esOpcional, double tiempoDuracion) {
        this.nombre = nombre;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.esOpcional = esOpcional;
        this.tiempoDuracion=tiempoDuracion;
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

    public boolean isEsOpcional() {
        return esOpcional;
    }

    public void setEsOpcional(boolean esOpcional) {
        this.esOpcional = esOpcional;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    public double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(double tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }
}
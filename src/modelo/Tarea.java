package modelo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Tarea implements Cloneable, Serializable{

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleStringProperty nombreActividad = new SimpleStringProperty();
    private SimpleStringProperty descripcion = new SimpleStringProperty();
    private SimpleBooleanProperty esOpcional = new SimpleBooleanProperty();
    private double tiempoDuracion = 0;

    public Tarea() {

    }

    public Tarea(String nombreActividad, String nombre, String descripcion, boolean esOpcional, double tiempoDuracion) {
        this.nombre.set(nombre);
        this.nombreActividad.set(nombreActividad);
        this.descripcion.set(descripcion);
        this.esOpcional.set(esOpcional);
        this.tiempoDuracion = tiempoDuracion;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public boolean isEsOpcional() {
        return esOpcional.get();
    }

    public void setEsOpcional(boolean esOpcional) {
        this.esOpcional.set(esOpcional);
    }

    public String getNombreActividad() {
        return nombreActividad.get();
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad.set(nombreActividad);
    }

    public double getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(double tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }
}
package modelo;

import listas.Cola;
import listas.ListaSimple;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatorio;
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();
    private int codigoProceso;
    
    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;
        this.codigoProceso = codigoProceso;
    }


    public Actividad() {
    }

    public String getNombre(){
        return this.nombre;
    }
}

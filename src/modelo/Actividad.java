package modelo;

import listas.Cola;
import listas.ListaSimple;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatorio;
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();
    private int codigoProceso;
    private int codigo;
    
    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;
        this.codigoProceso = codigoProceso;
        codigo++;
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigo,  int codigoProceso){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;
        this.codigoProceso = codigoProceso;
        this.codigo = codigo;
    }


    public Actividad() {
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
}

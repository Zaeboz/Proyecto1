package modelo;

import listas.Cola;
import listas.ListaSimple;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatorio;
    private Cola<Tarea> colaDeTareas=new Cola<Tarea>();
}

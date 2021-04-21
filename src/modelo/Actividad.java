package modelo;

import listas.Cola;
import listas.ListaSimple;

public class Actividad {

    private String nombre;
    private String descripcion;
    private boolean esObligatorio;
    private Cola<Tarea> colaDeTareas=new Cola<Tarea>();
    private int codigo;
    
    public void crearActividad(String nombre2, String descripcion2, String codigoProceso, Boolean esObligatoria) {
        codigo++;
        
    }
}

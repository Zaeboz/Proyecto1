package modelo;


import java.io.Serializable;

import exeptions.TareasNoObligatoriasException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import listas.Cola;


public class Actividad implements Cloneable, Serializable{

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleIntegerProperty codigoProceso = new SimpleIntegerProperty();
    private SimpleStringProperty descripcion = new SimpleStringProperty();
    private SimpleBooleanProperty esObligatoria = new SimpleBooleanProperty();
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();

    private SimpleDoubleProperty tiempoMaximo = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty tiempoMinimo = new SimpleDoubleProperty(0);

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo.set(tiempoMaximo);
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

    public boolean getEsObligatoria() {
        return esObligatoria.get();
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria.set(esObligatoria);
    }

    public Cola<Tarea> getColaDeTareas() {
        return colaDeTareas;
    }

    public void setColaDeTareas(Cola<Tarea> colaDeTareas) {
        this.colaDeTareas = colaDeTareas;
    }

    public int getCodigoProceso() {
        return codigoProceso.get();
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso) {
        this.nombre.set(nombre);
        this.descripcion.set(descripcion);
        this.esObligatoria.set(esObligatorio);
        this.codigoProceso.set(codigoProceso);
    }
    
    public Actividad() {
    }

    /**
     * Metodo que crea una tarea al final de la cola de tareas
     *
     * @param tarea
     * @throws TareasNoObligatoriasException
     */
    public void crearTareaAlFinal(Tarea tarea) throws TareasNoObligatoriasException {
        Tarea miTareaAuxiliar = colaDeTareas.obtenerUltimoElemento();
        if(miTareaAuxiliar == null)
        {
            colaDeTareas.encolar(tarea);
        }else if (tarea.isObligatoria() && miTareaAuxiliar.isObligatoria()) {
            throw new TareasNoObligatoriasException("La siguiente tarea debe ser opcional");
        } else {
            colaDeTareas.encolar(tarea);
        }
    }

    public void modificarIDColaTarea(){
        Cola<Tarea> colaAux = new Cola<>();
        Tarea tareaAux = new Tarea();
        int size = colaDeTareas.getTamanio();
        for(int i = size; i > 0; i--){
            tareaAux = colaDeTareas.desencolar();
            tareaAux.setNombreActividad(nombre.getValue());
            colaAux.encolar(tareaAux);
        }
        colaDeTareas = colaAux;
    }

    /**
     * Metodo que crea una tarea en una posicion
     * @param tarea
     * @param posicion
     * @return
     */
    public Cola<Tarea> crearTareaEnPosicion(Tarea tarea, int posicion) {
        Cola<Tarea> nuevaCola = new Cola<>();
        int size = getColaDeTareas().getTamanio();
        for(int i = 0; i <= size; i++){
            if(posicion-1==i) nuevaCola.encolar(tarea);
            else nuevaCola.encolar(getColaDeTareas().desencolar());
        }
        return nuevaCola;
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public double getTiempoMaximo() {
        return tiempoMaximo.get();
    }

    public double getTiempoMinimo() {
        return tiempoMinimo.get();
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo.set(tiempoMinimo);
    }

    public void setCodigoProceso(int codigoProceso) {
        this.codigoProceso.set(codigoProceso);
    }

    @Override
    public String toString(){
        return ("Nombre: "+nombre.getValue()+", Codigo del proceso: "+codigoProceso.getValue()+
                ", Descripcion: "+descripcion.getValue()+", ¿Es obligatoria?:  "+esObligatoria.getValue());
    }
}
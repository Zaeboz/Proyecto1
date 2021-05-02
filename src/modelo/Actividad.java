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

    /**
     * Metodo que crea una tarea al final de la cola de tareas
     *
     * @param descripcion
     * @param esObligatoria
     * @param tiempoDuracion
     * @throws CloneNotSupportedException
     * @throws TareasNoObligatoriasException
     */
    public void crearTareaAlFInal(String descripcion, boolean esObligatoria, double tiempoDuracion) throws CloneNotSupportedException, TareasNoObligatoriasException {
        Cola<Tarea> colaDeTareasAuxiliar = new Cola<>();


        Tarea miTareaAuxiliar = colaDeTareas.obtenerUltimoElemento();
        Tarea tareaAñadir;
        if (!esObligatoria && !miTareaAuxiliar.isEsObligatoria()) {
            throw new TareasNoObligatoriasException("La siguiente tarea también es opcional");
        } else {
            tareaAñadir = new Tarea(descripcion, esObligatoria, tiempoDuracion);
            colaDeTareas.encolar(tareaAñadir);
        }
    }

    /**
     * Metodo que crea una tarea en una posicion
     *
     * @param descripcion
     * @param esObligatoria
     * @param tiempoDuracion
     * @param poscionDondeInserta
     */
    public void crearTareaEnPosicion(String descripcion, boolean esObligatoria, double tiempoDuracion, int poscionDondeInserta) {
        Tarea tareaAInsertar = new Tarea(descripcion, esObligatoria, tiempoDuracion);
        colaDeTareas.insertarElemento(tareaAInsertar, poscionDondeInserta);
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso) {
        this.nombre.set(nombre);
        this.descripcion.set(descripcion);
        this.esObligatoria.set(esObligatorio);
        this.codigoProceso.set(codigoProceso);
    }
    
    public Actividad() {
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
        return ("Nombre: "+nombre+", Codigo del proceso: "+codigoProceso+
        ", Descripcion: "+descripcion+", ¿Es obligatoria?:  "+esObligatoria);
    }
}


package modelo;


import exeptions.TareasNoObligatoriasException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import listas.Cola;

import java.io.Serializable;


public class Actividad implements Cloneable, Serializable{

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty nombre = new SimpleStringProperty();
    private SimpleIntegerProperty codigoProceso = new SimpleIntegerProperty();
    private SimpleStringProperty descripcion = new SimpleStringProperty();
    private SimpleBooleanProperty esObligatoria = new SimpleBooleanProperty();
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();

    public double getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public double getTiempoMinimo() {
        return tiempoMinimo;
    }

    private double tiempoMaximo = 0;
    private double tiempoMinimo = 0;

    public Actividad() {
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso) {
        this.nombre.set(nombre);
        this.descripcion.set(descripcion);
        this.esObligatoria.set(esObligatorio);
        this.codigoProceso.set(codigoProceso);
    }



    public void calcularDuracionActividad() throws CloneNotSupportedException {
        Cola<Tarea> colaTareasAuxClonada=new Cola<Tarea>();
        colaTareasAuxClonada= (Cola<Tarea>) this.getColaDeTareas().clone();
        Tarea tareaAux=new Tarea();
        double tiempoMinimo=0;
        double tiempoMaximo=0;

        for (int k=0;k<colaTareasAuxClonada.getTamano();k++)
        {
            tareaAux=colaTareasAuxClonada.desencolar();
            if(tareaAux.isEsOpcional())
            {
                tiempoMinimo=tiempoMinimo+tareaAux.getTiempoDuracion();

            }else{
                tiempoMaximo=tiempoMaximo+tareaAux.getTiempoDuracion();
            }
        }

        this.setTiempoMinimo(tiempoMinimo);
        this.setTiempoMaximo(tiempoMaximo);

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
        }else if (tarea.isEsOpcional() && miTareaAuxiliar.isEsOpcional()) {
            throw new TareasNoObligatoriasException("La siguiente tarea debe ser opcional");
        } else {
            colaDeTareas.encolar(tarea);
        }
    }

    /**
     * Metodo para modificar el ID de actividad de las tareas. Esto sdebe de ejecutar
     * ya que al intercambiar las tareas de 2 actividades tambien se debe de modificar
     * el nombre ID de actividad de las tareas
     */
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
    public void crearTareaEnPosicion(Tarea tarea, int posicion) {
        Cola<Tarea> nuevaCola = new Cola<>();
        if(colaDeTareas.validarPosicion(posicion)){
            int size = getColaDeTareas().getTamanio();
            for(int i = 0; i <= size; i++){
                if(posicion-1==i) nuevaCola.encolar(tarea);
                else nuevaCola.encolar(getColaDeTareas().desencolar());
            }
            colaDeTareas = nuevaCola;
        }else{
            //Poner vantana emergente
        }
    }

    /**
     * Metodo para modificar el ID de la actividad a la que pertenece la tarea.
     * Este metodo solo se ejecuta cuando se modifica o se edita una actividad
     * @param nombreActividad Nuevo nombre de la actividad o ID
     */
    public void modificarNombreActicidadTareas(String nombreActividad)  {
        Cola<Tarea> tareasClone = new Cola<>();
        int size = colaDeTareas.getTamanio();

        if(size!=0){
            for(int i = 0; i < size; i++){
                Tarea tareaAux = colaDeTareas.desencolar();
                tareaAux.setNombreActividad(nombreActividad);
                tareasClone.encolar(tareaAux);
            }

            for(int i = 0; i < size; i++){
                colaDeTareas.encolar(tareasClone.desencolar());
            }
        }
    }

    /**
     * Metodo para eliminar una tarea de la cola de tareas
     * @param tarea Tarea a eliminar
     * @throws CloneNotSupportedException
     */
    public void eliminarTarea(Tarea tarea) throws CloneNotSupportedException {
        int sizeCola = colaDeTareas.getTamanio();

        Cola<Tarea> colaTareasFinal = new Cola<>();
        Cola<Tarea> colaTareasCopia = (Cola<Tarea>) colaDeTareas.clone();

        for(int i  = 0; i < sizeCola; i++){
            Tarea tarea1 = colaTareasCopia.desencolar();
            if(tarea!=tarea1) colaTareasFinal.encolar(tarea1);
        }

        colaDeTareas = colaTareasFinal;
    }

    /**
     * Metodo para buscar una tarea dentro de la cola de tareas
     * @param nombreTarea Nombre de la tarea a buscar
     * @return La tarea encontrada de lo contrario retorna null
     * @throws CloneNotSupportedException
     */
    public Tarea buscarTarea(String nombreTarea) throws CloneNotSupportedException {
        Cola<Tarea> colaTereas = (Cola<Tarea>) colaDeTareas.clone();
        int size = colaTereas.getTamanio();
        for (int i = 0; i < size; i++){
            Tarea tareaAux = colaTereas.desencolar();
            if(nombreTarea.equals(tareaAux.getNombre())){
                return tareaAux;
            }
        }
        return null;
    }

    public void editarTarea(Tarea tareaNueva, String nombreTareaAnterior) throws CloneNotSupportedException {
        int sizeCola = colaDeTareas.getTamanio();

        Cola<Tarea> colaTareasFinal = new Cola<>();
        Cola<Tarea> colaTareasCopia = (Cola<Tarea>) colaDeTareas.clone();
        Tarea tarea = buscarTarea(nombreTareaAnterior);

        for(int i  = 0; i < sizeCola; i++){
            Tarea tarea1 = colaTareasCopia.desencolar();
            if(tarea==tarea1){
                tarea1.setNombre(tareaNueva.getNombre());
                tarea1.setTiempoDuracion(tareaNueva.getTiempoDuracion());
                tarea1.setDescripcion(tareaNueva.getDescripcion());
                tarea1.setEsOpcional(tareaNueva.isEsOpcional());
                colaTareasFinal.encolar(tarea1);
            }else{
                colaTareasFinal.encolar(tarea1);
            }
        }
        colaDeTareas = colaTareasFinal;
    }

    public Object clone( ) throws CloneNotSupportedException{
        return super.clone();
    }
    @Override
    public String toString(){
        return ("Nombre: "+nombre.getValue()+", Codigo del proceso: "+codigoProceso.getValue()+
                ", Descripcion: "+descripcion.getValue()+", ¿Es obligatoria?:  "+esObligatoria.getValue());
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo=(tiempoMinimo);
    }

    public void setCodigoProceso(int codigoProceso) {
        this.codigoProceso.set(codigoProceso);
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
}
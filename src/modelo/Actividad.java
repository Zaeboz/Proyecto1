package modelo;


import exeptions.TareasNoObligatoriasException;
import listas.Cola;


public class Actividad implements Cloneable {

    private String descripcion;
    private boolean esObligatoria;
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();
    private int codigoProceso;

    private double tiempoMaximo;
    private double tiempoMinimo;

    private String nombre;

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
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

    public boolean isEsObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public Cola<Tarea> getColaDeTareas() {
        return colaDeTareas;
    }

    public void setColaDeTareas(Cola<Tarea> colaDeTareas) {
        this.colaDeTareas = colaDeTareas;
    }

    public int getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(int codigoProceso) {
        this.codigoProceso = codigoProceso;
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

    public void buscarTarea(int formaDeBuscar) {
        /**
         *
         *
         switch (formaDeBuscar) {
         case 0:
         buscarTareaInicio();
         break;
         case 1:
         buscarTareaActividadActual();
         break;
         case 3:
         buscarTarea();
         break;
         }
         **/
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatoria = esObligatorio;
        this.codigoProceso = codigoProceso;
    }


    public Actividad() {
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getTiempoMaximo() {
        return tiempoMaximo;
    }

    public double getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }
}


package modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import listas.Cola;
import listas.ListaDoble;

public class Proceso {

    private SimpleStringProperty nombreProceso ;
    private SimpleIntegerProperty idProceso;
    private SimpleDoubleProperty tiempoMaximo;
    private SimpleDoubleProperty tiempoMinimo;

    public String getNombreProceso() {
        return nombreProceso.get();
    }

    public SimpleStringProperty nombreProcesoProperty() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso)
    {
        this.nombreProceso.set(nombreProceso);
    }

    public int getIdProceso() {
        return idProceso.get();
    }

    public SimpleIntegerProperty idProcesoProperty() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso.set(idProceso);
    }

    public double getTiempoMaximo() {
        return tiempoMaximo.get();
    }

    public SimpleDoubleProperty tiempoMaximoProperty() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo.set(tiempoMaximo);
    }

    public double getTiempoMinimo() {
        return tiempoMinimo.get();
    }

    public SimpleDoubleProperty tiempoMinimoProperty() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo.set(tiempoMinimo);
    }



    private ListaDoble<Actividad> listaActividades = new ListaDoble<>();

    public Proceso(String nombreProceso,int idProceso) {
        this.nombreProceso = new SimpleStringProperty (nombreProceso);
        this.idProceso = new SimpleIntegerProperty (idProceso);
    }
    
    public Proceso(String nombreProceso,int idProceso,Double tiempoMaximo,Double tiempoMinimo) {
        this.nombreProceso = new SimpleStringProperty (nombreProceso);
        this.idProceso = new SimpleIntegerProperty (idProceso);
        this.tiempoMaximo= new SimpleDoubleProperty(tiempoMaximo);
        this.tiempoMinimo=new SimpleDoubleProperty(tiempoMinimo);
    }

    public Proceso()
    {

    }



    public ListaDoble<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ListaDoble<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }


    public void crearActividadFinal(String nombre, String descripcion, Boolean esObligatoria, int iDProceso) {
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
        if (validarActivid(actividad)) listaActividades.agregarfinal(actividad);
        else System.out.println("Actividad repetida");
    }

    public void crearActividadDespues(String nombre, String descripcion, Boolean esObligatoria, int iDProceso, String nombreActividadAnterior) {
        Actividad actividadAnterior = new Actividad();

        for (int i = 0; i < listaActividades.getTamanio(); i++) {
            actividadAnterior = listaActividades.obtener(i);
            if (nombreActividadAnterior.equals(actividadAnterior.getNombre())) {
                Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
                if (validarActivid(actividad)) listaActividades.agregar(actividad, i + 1);
                else System.out.println("Actividad repetida");
            }
        }
    }

    public void crearActividadDespuesUltima(String nombre, String descripcion, Boolean esObligatoria, int iDProceso) {
        Actividad actividadUltimaAgregada = listaActividades.getUltimoAgregado();

        int posicion = listaActividades.obtenerPosicionNodo(actividadUltimaAgregada);
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
        if (validarActivid(actividad)) listaActividades.agregar(actividad, posicion + 1);
        else System.out.println("Actividad repetida");
    }

    /**
     * @param actividad
     * @return
     */
    public Boolean validarActivid(Actividad actividad) {

        for (int i = 1; i < listaActividades.getTamanio() + 1; i++) {
            if (listaActividades.obtener(i).getNombre().equals(actividad.getNombre())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metood que calcula el tiempo maximo y minimo de una actividad
     *
     * @param actividadACalcular
     * @throws CloneNotSupportedException exepcion que controla la clonacion de la cola de actividades
     */
    public void calcularTiemposActividad(Actividad actividadACalcular) throws CloneNotSupportedException {
        Cola<Tarea> colaDeTareasAuxiliar = new Cola<Tarea>();
        Tarea tareaAuxiliar = new Tarea();
        double tiempoMaximo = 0;
        double tiempoMinimo = 0;


        colaDeTareasAuxiliar = actividadACalcular.getColaDeTareas();

        colaDeTareasAuxiliar = (Cola<Tarea>) colaDeTareasAuxiliar.clone();

        for (int j = 0; j < colaDeTareasAuxiliar.getTamano(); j++) {

            tareaAuxiliar = colaDeTareasAuxiliar.desencolar();

            if (tareaAuxiliar.isEsObligatoria()) {
                tiempoMinimo = tiempoMinimo + tareaAuxiliar.getTiempoDuracion();

            }
            tiempoMaximo = tiempoMaximo + tareaAuxiliar.getTiempoDuracion();
        }
        actividadACalcular.setTiempoMaximo(tiempoMaximo);
        actividadACalcular.setTiempoMinimo(tiempoMinimo);

    }

    /**
     * Metodo que calcula el tiempo maximo y minimo de duracion de un proceso
     *
     * @throws CloneNotSupportedException
     */
    public void calcularTiempoDuracionProceso() throws CloneNotSupportedException {
        ListaDoble<Actividad> listaActividadesAux = this.getListaActividades();
        Cola<Tarea> colaDeTareasAuxiliar = new Cola<Tarea>();

        Actividad actividadActualAuxiliar = new Actividad();

        Tarea tareaActualAuxiliar = new Tarea();

        double cantidadTiempoMaximoProceso = 0;
        double cantidadTiempoMinimoProceso = 0;


        for (int i = 0; i < listaActividadesAux.getTamanio(); i++) {

            actividadActualAuxiliar = listaActividadesAux.obtenerValorNodo(i);

            calcularTiemposActividad(actividadActualAuxiliar);
            if (actividadActualAuxiliar.isEsObligatoria()) {


                cantidadTiempoMinimoProceso = cantidadTiempoMinimoProceso + actividadActualAuxiliar.getTiempoMinimo();

            }
            cantidadTiempoMaximoProceso = cantidadTiempoMaximoProceso + actividadActualAuxiliar.getTiempoMaximo();
        }
    }

    /**
     * Metodo para buscar actividades dado un nombre
     *
     * @param nombreActividad nombre de la actividad a buscar
     * @return la actividad a buscar
     */
    public Actividad buscarActividad(String nombreActividad) {
        Actividad actividadAuxiliar = new Actividad();

        for (int i = 0; i < this.listaActividades.getTamanio(); i++) {


            actividadAuxiliar = this.listaActividades.obtenerValorNodo(i);

            if (actividadAuxiliar.getNombre().equals(nombreActividad)) {
                i = this.listaActividades.getTamanio();
            }

        }

        return actividadAuxiliar;
    }

    /**
     * Metodo para buscar actividades dado sus nombres
     *
     * @param nombreActi1 nombre de la actividad a buscar
     * @param nombreActi2 nombre de la actividad a buscar
     */
    public void intercambiarActividades(String nombreActi1, String nombreActi2) {
        Actividad actividad1 = new Actividad();
        Actividad actividad2 = new Actividad();
        Cola<Tarea> colaDeTareasAux1 = new Cola<Tarea>();
        Cola<Tarea> colaDeTareasAux2 = new Cola<Tarea>();
        actividad1 = buscarActividad(nombreActi1);
        actividad2 = buscarActividad(nombreActi2);

        colaDeTareasAux1 = actividad1.getColaDeTareas();
        colaDeTareasAux2 = actividad2.getColaDeTareas();

        actividad1.setColaDeTareas(colaDeTareasAux2);
        actividad2.setColaDeTareas(colaDeTareasAux1);


    }


}
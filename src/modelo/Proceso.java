package modelo;

import java.io.Serializable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import listas.Cola;
import listas.ListaDoble;
import listas.Pila;

public class Proceso implements Serializable, Cloneable{

    private static final long serialVersionUID = 1L;

    private SimpleStringProperty nombreProceso = new SimpleStringProperty("0");
    private SimpleIntegerProperty idProceso = new SimpleIntegerProperty(0);
    private double tiempoMaximo =0;
    private double tiempoMinimo = 0;
    private ListaDoble<Actividad> listaActividades = new ListaDoble<>();
    private Pila<Actividad> pilaActividadAux = new Pila<>();
    private Actividad actividadUltimoAgregado = new Actividad();

    public Object clone( ) throws CloneNotSupportedException{
        return super.clone();
    }

    //Constructores
    public Proceso(){

    }

    public Proceso(String nombreProceso, int idProceso){
        this.nombreProceso = new SimpleStringProperty (nombreProceso);
        this.idProceso = new SimpleIntegerProperty (idProceso);
    }

    public Proceso(String nombreProceso,int idProceso,Double tiempoMaximo,Double tiempoMinimo) {
        this.nombreProceso = new SimpleStringProperty (nombreProceso);
        this.idProceso = new SimpleIntegerProperty (idProceso);
        this.tiempoMaximo= (tiempoMaximo);
        this.tiempoMinimo= (tiempoMinimo);
    }

    /**
     * Metodo para agregar una actividad al final de la lista de actividades
     * @param actividad La nueva actividad a agregar
     */
    public void crearActividadFinal(Actividad actividad) {
        listaActividades.agregarfinal(actividad);
        actividadUltimoAgregado = actividad;
        pilaActividadAux.push(actividad);
    }

    /**
     * Metodo para agregar una actividad despues de una actividad en especifico
     * @param actividadAInsertar La nueva actividad que se va a agregar a la lista
     * @param nombreActividadAnterior El nombre de la actividad en donde se va a agregar despues
     */
    public void crearActividadDespues(Actividad actividadAInsertar, String nombreActividadAnterior) {
        Actividad actividadAnterior = new Actividad();

        for (int i = 0; i < listaActividades.getTamanio(); i++) {
            actividadAnterior = listaActividades.obtener(i);
            if (nombreActividadAnterior.equals(actividadAnterior.getNombre())) {
                System.out.println(""+nombreActividadAnterior.equals(actividadAnterior.getNombre()));
                if (validarActividad(actividadAInsertar)&&i+1<listaActividades.getTamanio()) {
                    listaActividades.agregar(actividadAInsertar, i + 1);
                    actividadUltimoAgregado = actividadAInsertar;
                }
                else if(i+1==listaActividades.getTamanio()){
                    listaActividades.agregarfinal(actividadAInsertar);
                    actividadUltimoAgregado = actividadAInsertar;
                }
            }
        }
        pilaActividadAux.push(actividadAInsertar);
    }

    /**
     * Metodo para agregar una actividad despues de la ultima actividad agregada en la
     * lista
     * @param actividad La nueva actividad a agregar
     */
    public void crearActividadDespuesUltima(Actividad actividad) {

        crearActividadDespues(actividad, actividadUltimoAgregado.getNombre());

    }

    /**
     * @param actividad
     * @return
     */
    public Boolean validarActividad(Actividad actividad) {

        if(actividad != null){
            for (int i = 1; i < listaActividades.getTamanio(); i++) {
                if (listaActividades.obtener(i).getNombre().equals(actividad.getNombre())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Metodo para buscar actividades dado un nombre
     *
     * @param nombreActividad nombre de la actividad a buscar
     * @return la actividad encontrada
     */
    public Actividad buscarActividad(String nombreActividad) {
        Actividad actividadAuxiliar = new Actividad();

        for (int i = 0; i < this.listaActividades.getTamanio(); i++) {

            actividadAuxiliar = this.listaActividades.obtenerValorNodo(i);

            if(nombreActividad!=null){
                if (actividadAuxiliar.getNombre().equals(nombreActividad)) {
                    return actividadAuxiliar;
                }
            }
        }

        return null;
    }

    /**
     * Metodo para eliminar una actividad en especifico dentro de la lista de
     * actividades
     * @param actividadEliminar Actividad a eliminar
     */
    public void eliminarActividad(Actividad actividadEliminar){

        pilaActividadAux.imprimir();
        pilaActividadAux.pop();
        listaActividades.eliminar(actividadEliminar);
        pilaActividadAux.imprimir();
        if(pilaActividadAux.getTamano()==0){
            actividadUltimoAgregado = null;
        }
        else actividadUltimoAgregado = pilaActividadAux.obtenerCima();

    }

    /**
     * Metodo para intercambiar las tareas de 2 actividades  sin modificar la posicion de
     * las actividades dentro de la lista
     * @param nombreActi1 nombre de la actividad a buscar
     * @param nombreActi2 nombre de la actividad a buscar
     */
    public void intercambiarActividadesTareas(String nombreActi1, String nombreActi2) {
        Actividad actividad1 = buscarActividad(nombreActi1);
        Actividad actividad2 = buscarActividad(nombreActi2);
        if(actividad1!=null&&actividad2!=null){
            Cola<Tarea> colaDeTareasAux1 = actividad1.getColaDeTareas();
            Cola<Tarea> colaDeTareasAux2 = actividad2.getColaDeTareas();

            int pos1 = listaActividades.obtenerPosicionNodo(actividad1);
            int pos2 = listaActividades.obtenerPosicionNodo(actividad2);

            actividad1.setColaDeTareas(colaDeTareasAux2);
            actividad2.setColaDeTareas(colaDeTareasAux1);

            actividad1.modificarIDColaTarea();
            actividad2.modificarIDColaTarea();

            listaActividades.modificarNodo(pos1, actividad1);
            listaActividades.modificarNodo(pos2, actividad2);
        }
        //Falta poner la venta emergenta para la actividad que no existe
    }

    /**
     * Metodo para intercambiar dos actividades en la tabla de actividades
     * @param nombreActi1 Primera actividad a intercambiar
     * @param nombreActi2 Segunda actibidad a intercambiar
     */
    public void intercambiarActividades(String nombreActi1, String nombreActi2) {
        Actividad actividad1 = buscarActividad(nombreActi1);
        Actividad actividad2 = buscarActividad(nombreActi2);

        if(actividad1!=null&&actividad2!=null){
            int pos1 = listaActividades.obtenerPosicionNodo(actividad1);
            int pos2 = listaActividades.obtenerPosicionNodo(actividad2);

            listaActividades.modificarNodo(pos2, actividad1);
            listaActividades.modificarNodo(pos1, actividad2);
        }
        //Falta poner la venta emergenta para la actividad que no existe
    }

    /**
     * Metodo para editar una actividad previamente selecionanda en la vista
     * @param actividadVieja La actividad seleccionada
     * @param nombreActividad Nurvo nombre de la actividad
     * @param esObligatoria Nuevo estado de la actividad
     * @param descripcion Nuava descripcion de la actividad
     */
    public void editarActividad(Actividad actividadVieja, String nombreActividad, Boolean esObligatoria, String descripcion) {
        actividadVieja = buscarActividad(actividadVieja.getNombre());
        Actividad actividadNueva = new Actividad();
        int i;

        for(i = 0; i < listaActividades.getTamanio(); i++){
            if(actividadVieja==listaActividades.obtenerValorNodo(i)){
                actividadNueva = actividadVieja;
                actividadNueva.setNombre(nombreActividad);
                actividadNueva.setEsObligatoria(esObligatoria);
                actividadNueva.setDescripcion(descripcion);

                actividadNueva.modificarNombreActicidadTareas(nombreActividad);

                listaActividades.modificarNodo(i, actividadNueva);
            }
        }
    }

    /**
     *
     * @throws CloneNotSupportedException
     */
    public void calcularDuracionProceso() throws CloneNotSupportedException {
        ListaDoble<Actividad> listaActividadAux = new ListaDoble<Actividad>();

        listaActividadAux = (ListaDoble<Actividad>) this.getListaActividades().clone();

        Actividad actividadAux = new Actividad();

        double timpoMinimo = 0.0;
        double tiempoMaximo = 0.0;


        for (int i = 0; i < listaActividadAux.getTamanio(); i++) {

            actividadAux = listaActividadAux.obtenerValorNodo(i);
            actividadAux.calcularDuracionActividad();

            if (actividadAux.getEsObligatoria())
            {
                tiempoMinimo = tiempoMinimo+actividadAux.getTiempoMinimo();
            }else{
                tiempoMaximo=tiempoMaximo+actividadAux.getTiempoMaximo();
            }

        }

        this.tiempoMinimo = tiempoMinimo;
        this.tiempoMaximo = tiempoMaximo;
    }

    //Getters and setters
    public ListaDoble<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ListaDoble<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

	public void setNombreProceso(SimpleStringProperty nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public void setIdProceso(SimpleIntegerProperty idProceso) {
		this.idProceso = idProceso;
	}


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

    public Pila<Actividad> getPilaActividadAux() {
        return pilaActividadAux;
    }

    public void setPilaActividadAux(Pila<Actividad> pilaActividadAux) {
        this.pilaActividadAux = pilaActividadAux;
    }

    public Actividad getActividadUltimoAgregado() {
        return actividadUltimoAgregado;
    }

    public void setActividadUltimoAgregado(Actividad actividadUltimoAgregado) {
        this.actividadUltimoAgregado = actividadUltimoAgregado;
    }


    public double getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(double tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public double getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void setTiempoMinimo(double tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }

}
package modelo;

import application.Main;
import exeptions.TareasNoObligatoriasException;
import listas.ListaDoble;
import listas.ListaSimple;
import persistencia.Persistencia;

import java.io.Serializable;

public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListaSimple<Proceso> listaProcesos = new ListaSimple<>();
    int idProceso = 0;

    public Proyecto() {

    }

    //Metodos de los procesos.

    /**
     * Metodo para crear un proceso y agregarlo al final de la lista de
     * procesos
     * @param procesoNuevo El proceso a crear
     */
    public void crearProceso(Proceso procesoNuevo) {

        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            idProceso = listaProcesos.obtenerValorNodo(i).getIdProceso()+1;
        }
        procesoNuevo.setIdProceso(idProceso);
        listaProcesos.agregarfinal(procesoNuevo);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo para eliminar un proceso de la lista de procesos
     * @param procesoSelecionado El proceso a eliminar
     */
    public void eliminarProceso(Proceso procesoSelecionado) {
        Proceso procesoAux = buscarProcesoPorCodigo(procesoSelecionado.getIdProceso());
        listaProcesos.eliminar(procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo para buscar un proceso por codigo dentro de la lista
     * de procesos
     * @param codigo El codigo del proceso a buscar
     * @return El proceso encontrado si no lo encuentra retorna null
     */
    private Proceso buscarProcesoPorCodigo(int codigo){
        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            if(listaProcesos.obtenerValorNodo(i).getIdProceso()==codigo){
                Proceso proceso =  listaProcesos.obtenerValorNodo(i);
                return proceso;
            }
        }
        return null;
    }

    /**
     * Metodo para editar un proceso dentro de la lista de procesos
     * @param procesoAMostrar El proceso seleccionado para editar
     * @param nombreProceso Nuevo nombre del proceso
     * @param ideProceso Nuevo codigo del proceso
     */
    public void editarProceso(Proceso procesoAMostrar,String nombreProceso, int ideProceso){ 
        int posicionProcesoLogica = 0;
        for (int j=0;j<listaProcesos.getTamanio();j++) {
            if(listaProcesos.obtenerValorNodo(j) == procesoAMostrar) posicionProcesoLogica=j;
        }

        procesoAMostrar.setNombreProceso(nombreProceso);
        procesoAMostrar.setIdProceso(ideProceso);

        listaProcesos.modificarNodo(posicionProcesoLogica,procesoAMostrar);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    //Metodos de las actividades

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder editar una
     * actividad
     * @param actividadAux La actividad que se va a editar
     * @param nombreActividad Nuevo nombre de la actividad
     * @param esObligatoria Nuevo estado de la actividad
     * @param descripcion Nueva descripcion de la actividad
     */
    public void editarActividad(Actividad actividadAux, String nombreActividad, Boolean esObligatoria, String descripcion) {

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            listaProcesos.obtenerValorNodo(i).editarActividad(actividadAux, nombreActividad, esObligatoria, descripcion);
        }

        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder agregar una actividad
     * despues de la ultima agregada
     * @param actividadAux Nueva actividad a agregar a la lista de actividades de un proceso
     */
    public void crearActividadDespuesUltima(Actividad actividadAux) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespuesUltima(actividadAux);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     *  Metodo de comunicacion entre el proyecto y la clase proceso para poder agregar una actividad
     *  al final
     * @param actividadAux Actividad que se va a agregar al final de la lista
     */
    public void crearActividadFinal(Actividad actividadAux){
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadFinal(actividadAux);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder agregar una actividad
     * despues de una actividad en especifico
     * @param actividadAux Actividad que se va a agregar despues de la indicada
     * @param actividadAnterior Actividad que se va a usar para agregar una nueva despues de ella
     */
    public void crearActividadDespues(Actividad actividadAux, String actividadAnterior) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespues(actividadAux, actividadAnterior);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder intercambiar 2
     * actividades de posicion dentro de la tabla
     * @param actividad1 Primera actividad a intercambiar
     * @param actividad2 Segunda actividad a intercambiar
     */
    public void intercambiarActividades(Actividad actividad1, Actividad actividad2) {
        Proceso procesoAux = buscarProcesoPorCodigo(actividad1.getCodigoProceso());
        String nombreActividad1 = actividad1.getNombre();
        String nombreActividad2 = actividad2.getNombre();
        procesoAux.intercambiarActividades(nombreActividad1, nombreActividad2);
        int posicionProceso = listaProcesos.obtenerPosicionNodo(procesoAux);
        listaProcesos.modificarNodo(posicionProceso, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
	}

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder intercambiar los
     * procesos de 2 actividad sin modificar la posicion de las actividades dentro de la lista
     * @param actividad1 Primera actividad a intercambiar
     * @param actividad2 Segunda actividad a intercambiar
     */
	public void intercambiarActividadesTareas(Actividad actividad1, Actividad actividad2) {
        Proceso procesoAux = buscarProcesoPorCodigo(actividad1.getCodigoProceso());
        String nombreActividad1 = actividad1.getNombre();
        String nombreActividad2 = actividad2.getNombre();
        procesoAux.intercambiarActividadesTareas(nombreActividad1, nombreActividad2);
        int posicionProceso = listaProcesos.obtenerPosicionNodo(procesoAux);
        listaProcesos.modificarNodo(posicionProceso, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la clase proceso para poder eliminar una
     * actividad de la lista de actividades
     * @param actividad Actividad a eliminar
     */
    public void eliminarActividad(Actividad actividad) {
        actividad = buscarActividadEnProcesos(actividad.getNombre());
        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(procesoAux);
        procesoAux.eliminarActividad(actividad);
        listaProcesos.modificarNodo(posicion, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
        procesoAux.getPilaActividadAux();
    }

    /**
     *
     * @param nombreActividad
     * @return
     */
    public ListaSimple<Proceso> buscarProcesosEnActividades(String nombreActividad)  {
        ListaSimple<Proceso> listaProcesosEncontrados = new ListaSimple<>();

        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            Proceso procesoAux = listaProcesos.obtenerValorNodo(i);
            ListaDoble<Actividad> listaAuxActividades = procesoAux.getListaActividades();
            for(int a = 0; a < listaAuxActividades.getTamanio(); a++){
                Actividad actividadAux = listaAuxActividades.obtenerValorNodo(a);
                if(actividadAux.getNombre().equals(nombreActividad)){
                    listaProcesosEncontrados.agregarfinal(procesoAux);
                }
            }
        }
        return listaProcesosEncontrados;
    }

    /**
     * Metodo para buscar una actividad dentro de la lista de procesos
     * @param nombre El nombre de la actividad a buscar
     * @return La actividad encontrada si no la encuentra retorna null
     */
    public Actividad buscarActividadEnProcesos(String nombre) {

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            Proceso procesoAux = listaProcesos.obtenerValorNodo(i);
            Actividad actividadAux = procesoAux.buscarActividad(nombre);
            if(actividadAux!=null){
                return actividadAux;
            }
        }
        return null;
    }

    //Metodos tareas

    /**
     * Metodo de de comunicacion entre el proyecto y la actividad para poder
     * agregar una tarea a la lista de tareas
     * @param tarea
     * @throws CloneNotSupportedException
     * @throws TareasNoObligatoriasException
     */
    public void crearTareaFinal(Tarea tarea) throws CloneNotSupportedException, TareasNoObligatoriasException {
        Actividad actividad = buscarActividadEnProcesos(tarea.getNombreActividad());
        //Se agrega la tarea
        actividad.crearTareaAlFinal(tarea);

        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();

        listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
        procesoAux.setListaActividades(listaActividades);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la actividad para poder
     * agregar una tarea en una posicion especifica
     * @param tarea Tarea a agregar
     * @param posicion Posicion dentro de la cola
     */
    public void crearTareaPosicion(Tarea tarea, int posicion) {
        Actividad actividad = buscarActividadEnProcesos(tarea.getNombreActividad());
        //Se agrega la tarea
        actividad.crearTareaEnPosicion(tarea, posicion);

        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();

        listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
        procesoAux.setListaActividades(listaActividades);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    /**
     * Metodo de comunicacion entre el proyecto y la actividad para poder eliminar
     * una tarea de la cola de tareas
     * @param tarea Tarea a eliminar
     * @throws CloneNotSupportedException
     */
    public void eliminarTarea(Tarea tarea) throws CloneNotSupportedException {
        Tarea tareaAux = burcarTarea(tarea.getNombreActividad(), tarea.getNombre());
        if(tareaAux!=null){
            Actividad actividad = buscarActividadEnProcesos(tareaAux.getNombreActividad());
            //Se eliminar la tarea
            actividad.eliminarTarea(tareaAux);

            Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
            ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();

            listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
            procesoAux.setListaActividades(listaActividades);
            listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
            Persistencia.guardarRecursoProyectoXML(Main.proyecto);
        }
        //falta poner venta por si la tarea no existe
    }

    /**
     * Metodo Metodo de comunicacion entre el proyecto y la actividad para poder buscar
     * una tarea dentro de la cola de tareas
     * @param nombreActividad Nombre de la actividad en donde esta la tarea
     * @param nombreTarea Nombre de la tarea a buscar
     * @return La tarea encontrado si no la encuentra retorna null
     * @throws CloneNotSupportedException
     */
    private Tarea burcarTarea(String nombreActividad, String nombreTarea) throws CloneNotSupportedException {
        Actividad activdadAux = buscarActividadEnProcesos(nombreActividad);

        return activdadAux.buscarTarea(nombreTarea);
    }

    /**
     *
     * @param tareaNueva
     * @param nombreTareaAnterior
     * @throws CloneNotSupportedException
     */
    public void editarTarea(Tarea tareaNueva, String nombreTareaAnterior) throws CloneNotSupportedException {

        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            int sizeLista = listaProcesos.obtenerValorNodo(i).getListaActividades().getTamanio();
            for(int a = 0; a < sizeLista; a++){
                listaProcesos.obtenerValorNodo(i).getListaActividades().obtener(a).editarTarea(tareaNueva, nombreTareaAnterior);
            }
        }
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    //Getters and setters
    public ListaSimple<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public void setListaProcesos(ListaSimple<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

}
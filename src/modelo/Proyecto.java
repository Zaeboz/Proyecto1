package modelo;

import java.io.Serializable;

import application.Main;
import exeptions.TareasNoObligatoriasException;
import listas.Cola;
import listas.ListaDoble;
import listas.ListaSimple;
import persistencia.Persistencia;
//Hay que poner todos los metodos de crear aca
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListaSimple<Proceso> listaProcesos = new ListaSimple<>();
    int idProceso = 1;

    public Proyecto() {

    }

    //Metodos de los procesos.
    public void crearProceso(Proceso procesoNuevo) {
        listaProcesos.agregarfinal(procesoNuevo);
        idProceso++;
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void eliminarProceso(Proceso procesoSelecionado) {
        Proceso procesoAux = buscarProcesoPorCodigo(procesoSelecionado.getIdProceso());
        listaProcesos.eliminar(procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public Proceso buscarProcesosPorNombre(String nombre){
        Proceso procesoAux=new Proceso();

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            procesoAux= listaProcesos.obtenerValorNodo(i);
            if(procesoAux.getNombreProceso().equals(nombre)){
                procesoAux=listaProcesos.obtenerValorNodo(i);
                i = listaProcesos.getTamanio();
            }
        }
        return procesoAux;
    }

    private Proceso buscarProcesoPorCodigo(int codigo){
        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            if(listaProcesos.obtenerValorNodo(i).getIdProceso()==codigo){
                Proceso proceso =  listaProcesos.obtenerValorNodo(i);
                return proceso;
            }
        }
        return null;
    }

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
    public void editarActividad(Actividad actividadAux, String nombreActividad, Boolean esObligatoria, String descripcion) {
        int posicionActividadLogica = 0;
        int posicionProcesoLogica = 0;
        ListaDoble<Actividad> lista = new ListaDoble<>();
        Actividad actividad = buscarActividad(actividadAux.getNombre());

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            Proceso procesoAux= listaProcesos.obtenerValorNodo(i);
            ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();
            for (int a= 0; a < procesoAux.getListaActividades().getTamanio(); a++) {
                if(listaActividades.obtenerValorNodo(a)==actividad){ 
                    posicionActividadLogica = a;
                    posicionProcesoLogica = i; 
                    lista = listaActividades;
                }
            }
        }

        actividadAux.setNombre(nombreActividad);
        actividadAux.setDescripcion(descripcion);
        actividadAux.setEsObligatoria(esObligatoria);

        lista.modificarNodo(posicionActividadLogica, actividadAux);

        listaProcesos.obtenerValorNodo(posicionProcesoLogica).setListaActividades(lista);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearActividadDespuesUltima(Actividad actividadAux) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespuesUltima(actividadAux);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearActividadFinal(Actividad actividadAux){
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadFinal(actividadAux);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearActividadDespues(Actividad actividadAux, String actividadAnterior) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespues(actividadAux, actividadAnterior);
        proceso.setActividadUltimoAgregado(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void intercambiarActividades(Actividad actividad1, Actividad actividad2) {
        Proceso procesoAux = buscarProcesoPorCodigo(actividad1.getCodigoProceso());
        String nombreActividad1 = actividad1.getNombre();
        String nombreActividad2 = actividad2.getNombre();
        procesoAux.intercambiarActividades(nombreActividad1, nombreActividad2);
        int posicionProceso = listaProcesos.obtenerPosicionNodo(procesoAux);
        listaProcesos.modificarNodo(posicionProceso, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
	}

	public void intercambiarActividadesTareas(Actividad actividad1, Actividad actividad2) {
        Proceso procesoAux = buscarProcesoPorCodigo(actividad1.getCodigoProceso());
        String nombreActividad1 = actividad1.getNombre();
        String nombreActividad2 = actividad2.getNombre();
        procesoAux.intercambiarActividadesTareas(nombreActividad1, nombreActividad2);
        int posicionProceso = listaProcesos.obtenerPosicionNodo(procesoAux);
        listaProcesos.modificarNodo(posicionProceso, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public Actividad actualizarActividad(String nombre, String descripcion, int idProceso2, Boolean esObligatoria) {
        
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
        return null;
    }

    public Actividad actualizarActividad(Actividad actividadSeleccionada) {
        return null;
    }

    public void eliminarActividad(Actividad actividad) {
        actividad = buscarActividad(actividad.getNombre());
        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(procesoAux);
        procesoAux.eliminarActividad(actividad);
        listaProcesos.modificarNodo(posicion, procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
        procesoAux.getPilaActividadAux();
    }

    public ListaDoble<Actividad> buscarActividadesProceso(String nombre)  {
        Proceso procesoEncontrado = buscarProcesosPorNombre(nombre);
        ListaDoble<Actividad> listaProcesosAux = procesoEncontrado.getListaActividades();
        return listaProcesosAux;
    }

    public Actividad buscarActividad(String nombre) {
        ListaDoble<Actividad> listaActividades;
        Proceso procesoAux = new Proceso();

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            procesoAux = listaProcesos.obtenerValorNodo(i);
            listaActividades = procesoAux.getListaActividades();
            for (int a= 0; a < procesoAux.getListaActividades().getTamanio(); a++) {
                if(listaActividades.obtenerValorNodo(a).getNombre().equals(nombre)){
                    return listaActividades.obtenerValorNodo(a);
                }
            }
        }
        return null;
    }

    //Metodos tareas
    public void crearTareaFinal(Tarea tarea) throws CloneNotSupportedException, TareasNoObligatoriasException {
        Actividad actividad = buscarActividad(tarea.getNombreActividad());
        actividad.crearTareaAlFinal(tarea);

        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();

        listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
        procesoAux.setListaActividades(listaActividades);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearTareaPosicion(Tarea tarea, int posicion)
    {
        Actividad actividad = buscarActividad(tarea.getNombreActividad());
        Cola<Tarea> nuevaCola = actividad.crearTareaEnPosicion(tarea, posicion);
        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();

        actividad.setColaDeTareas(nuevaCola);
        listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
        procesoAux.setListaActividades(listaActividades);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void eliminarTarea(Tarea tarea) throws CloneNotSupportedException {
        Tarea tareaAux = burcarTarea(tarea.getNombreActividad(), tarea.getNombre());
        Actividad actividad = buscarActividad(tareaAux.getNombreActividad());
        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        ListaDoble<Actividad> listaActividades = procesoAux.getListaActividades();
        Cola<Tarea> colaTareasFinal = new Cola<>();
        Cola<Tarea> colaTareasCopia = (Cola<Tarea>) actividad.getColaDeTareas().clone();
        int sizeCola = colaTareasCopia.getTamanio();

        for(int i  = 0; i < sizeCola; i++){
            Tarea tarea1 = colaTareasCopia.desencolar();
            if(tareaAux!=tarea1) colaTareasFinal.encolar(tarea1);
        }

        actividad.setColaDeTareas(colaTareasFinal);
        listaActividades.modificarNodo(listaActividades.obtenerPosicionNodo(actividad), actividad);
        procesoAux.setListaActividades(listaActividades);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    private Tarea burcarTarea(String nombreActividad, String nombre) throws CloneNotSupportedException {
        Actividad activdadAux = buscarActividad(nombreActividad);
        Cola<Tarea> colaTereas = (Cola<Tarea>) activdadAux.getColaDeTareas().clone();
        int size = colaTereas.getTamanio();
        Tarea tareaAux = new Tarea();
        for (int i = 0; i < size; i++){
            tareaAux = colaTereas.desencolar();
            if(nombre.equals(tareaAux.getNombre())){
                return tareaAux;
            }
        }
        return null;
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
package modelo;

import java.io.Serializable;

import application.Main;
import listas.ListaDoble;
import listas.ListaSimple;
import persistencia.Persistencia;
//Hay que poner todos los metodos de crear aca
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    public ListaSimple<Proceso> listaProcesos = new ListaSimple<>();
    int idProceso = 1;

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

    public void editarProceso(Proceso procesoAMostrar,String nombreProceso, int ideProceso)
    { int posicionProcesoLogica=0;
        for (int j=0;j<listaProcesos.getTamanio();j++) {
            if(listaProcesos.obtenerValorNodo(j) == procesoAMostrar)
                posicionProcesoLogica=j;
        }

        procesoAMostrar.setNombreProceso(nombreProceso);
        procesoAMostrar.setIdProceso(ideProceso);

        listaProcesos.modificarNodo(posicionProcesoLogica,procesoAMostrar);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    //Metodos de las actividades
    public void crearActividadDespuesUltima(Actividad actividadAux) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespuesUltima(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearActividadFinal(Actividad actividadAux){
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadFinal(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void crearActividadDespues(Actividad actividadAux, String actividadAnterior) {
        Proceso proceso = buscarProcesoPorCodigo(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespues(actividadAux, actividadAnterior);
        listaProcesos.modificarNodo(posicion, proceso);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public void intercambiarActividades(Actividad actividad1, Actividad actividad2) {
        Proceso procesoAux = buscarProcesoPorCodigo(actividad1.getCodigoProceso());
        int posicionProceso = listaProcesos.obtenerPosicionNodo(procesoAux);
        int pos1 = procesoAux.getListaActividades().obtenerPosicionNodo(actividad1);
        int pos2 = procesoAux.getListaActividades().obtenerPosicionNodo(actividad2);
        procesoAux.getListaActividades().modificarNodo(pos1, actividad2);
        procesoAux.getListaActividades().modificarNodo(pos2, actividad1);
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

    public void eliminarActividad(String nombreActividad) {
        Actividad actividad = buscarActividad(nombreActividad);
        Proceso procesoAux = buscarProcesoPorCodigo(actividad.getCodigoProceso());
        procesoAux.getListaActividades().eliminar(actividad);
        listaProcesos.modificarNodo(listaProcesos.obtenerPosicionNodo(procesoAux), procesoAux);
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }

    public ListaDoble<Actividad> buscarActividadesProceso(String nombre)  {
        Proceso procesoEncontrado=buscarProcesosPorNombre(nombre);
        ListaDoble<Actividad> listaProcesosAux=procesoEncontrado.getListaActividades();
        return listaProcesosAux;
    }

    public Actividad buscarActividad(String nombre) {
        ListaDoble<Actividad> listaActividades;
        Proceso procesoAux=new Proceso();

        for (int i = 0; i < listaProcesos.getTamanio(); i++) {
            procesoAux= listaProcesos.obtenerValorNodo(i);
            listaActividades = procesoAux.getListaActividades();
            for (int a= 0; a < procesoAux.getListaActividades().getTamanio(); a++) {
                if(listaActividades.obtenerValorNodo(a).getNombre().equals(nombre)){
                    return listaActividades.obtenerValorNodo(a);
                }
            }
        }
        return null;
    }

    //Metodos de Tareas


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

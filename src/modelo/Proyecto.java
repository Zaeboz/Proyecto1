package modelo;

import listas.ListaDoble;
import listas.ListaSimple;
//Hay que poner todos los metodos de crear aca
public class Proyecto {

    public static ListaSimple<Proceso> listaProcesos = new ListaSimple<>();
    int idProceso = 0;

    public void crearProceso(String nombreProceso) {
        Proceso procesoNuevo= new Proceso(nombreProceso,idProceso);
        listaProcesos.agregarfinal(procesoNuevo);
        idProceso++;
    }

    public ListaSimple<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    public void crearActividadDespuesUltima(Actividad actividadAux) {
        Proceso proceso = buscarProceso(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespuesUltima(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
    }

    public void crearActividadFinal(Actividad actividadAux){
        Proceso proceso = buscarProceso(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadFinal(actividadAux);
        listaProcesos.modificarNodo(posicion, proceso);
    }

    public void crearActividadDespues(Actividad actividadAux, String actividadAnterior) {
        Proceso proceso = buscarProceso(actividadAux.getCodigoProceso());
        int posicion = listaProcesos.obtenerPosicionNodo(proceso);
        proceso.crearActividadDespues(actividadAux, actividadAnterior);
        listaProcesos.modificarNodo(posicion, proceso);
    }

    private Proceso buscarProceso(int codigo){
        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            if(listaProcesos.obtenerValorNodo(i).getIdProceso()==codigo){
                Proceso proceso =  listaProcesos.obtenerValorNodo(i);
                return proceso;
            }
        }
        return null;
    }

    public void eliminarProceso(Proceso procesoSelecionado) {

        listaProcesos.eliminar(procesoSelecionado);
    }

    public ListaDoble<Actividad> buscarActividadesProceso(String nombre)
    {
        Proceso procesoEncontrado=buscarProcesosPorNombre(nombre);

        ListaDoble<Actividad> listaProcesosAux=procesoEncontrado.getListaActividades();

        return listaProcesosAux;

    }

    public Proceso buscarProcesosPorNombre(String nombre)
    {
        Proceso procesoAux=new Proceso();
        for (int i = 0; i < listaProcesos.getTamanio(); i++) {

            procesoAux=this.listaProcesos.obtenerValorNodo(i);

            if(procesoAux.getNombreProceso().equals(nombre))
            {
                procesoAux=listaProcesos.obtenerValorNodo(i);
                i= listaProcesos.getTamanio();
            }
        }
        return procesoAux;
    }


}

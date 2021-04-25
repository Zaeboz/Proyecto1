package modelo;

import listas.ListaDoble;

public class Proceso {

    private String nombreProceso;
    private int idProcesos;
    private ListaDoble<Actividad> listaActividades = new ListaDoble<>();

    public void crearActividadFinal(String nombre, String descripcion, Boolean esObligatoria, int codigoProceso){
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, codigoProceso);
        listaActividades.agregarfinal(actividad);
    }

    public void crearActividadDespues(String nombre, String descripcion, Boolean esObligatoria, int codiogoActividadAntrior, int codigoProceso){
        Actividad actividadAnterior = new Actividad();

        for (int i = 0; i < listaActividades.getTamanio(); i++) {
            actividadAnterior = listaActividades.obtener(i);
            if(codiogoActividadAntrior == actividadAnterior.getCodigo()){
                Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, actividadAnterior.getCodigo()+1, codigoProceso);
                listaActividades.agregar(actividad, i+1);
                moverCodigos(actividad);
            }
        }
    }

    public void crearActividadDespuesUltima(String nombre, String descripcion, Boolean esObligatoria, int codigoProceso){
        Actividad actividadUltimaAgregada = listaActividades.getUltimoAgregado();
        
        int posicion = listaActividades.obtenerPosicionNodo(actividadUltimaAgregada);
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, posicion+1, codigoProceso);
        listaActividades.agregar(actividad, posicion+1);
        moverCodigos(actividad);
    }

    private void moverCodigos(Actividad actividad){
        Actividad actividadAux = new Actividad();
        for(int i = actividad.getCodigo()+1; i <= listaActividades.getTamanio(); i++){
            actividadAux = listaActividades.obtener(i);
            actividadAux.setCodigo(i);
            listaActividades.modificarNodo(i, actividadAux);
        }
    }
}

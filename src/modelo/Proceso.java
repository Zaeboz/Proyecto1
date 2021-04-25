package modelo;

import listas.ListaDoble;

public class Proceso {

    private String nombreProceso;
    private int idProcesos;
    private ListaDoble<Actividad> listaActividades = new ListaDoble<>();

    public void crearActividadFinal(String nombre, String descripcion, Boolean esObligatoria, int iDProceso){
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
        if(validarActivid(actividad)) listaActividades.agregarfinal(actividad);
        else System.out.println("Actividad repetida");
    }

    public void crearActividadDespues(String nombre, String descripcion, Boolean esObligatoria, int iDProceso, String nombreActividadAnterior){
        Actividad actividadAnterior = new Actividad();

        for (int i = 0; i < listaActividades.getTamanio(); i++) {
            actividadAnterior = listaActividades.obtener(i);
            if(nombreActividadAnterior.equals(actividadAnterior.getNombre())){
                Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
                if(validarActivid(actividad)) listaActividades.agregar(actividad, i+1);
                else System.out.println("Actividad repetida");
            }
        }
    }

    public void crearActividadDespuesUltima(String nombre, String descripcion, Boolean esObligatoria, int iDProceso){
        Actividad actividadUltimaAgregada = listaActividades.getUltimoAgregado();
        
        int posicion = listaActividades.obtenerPosicionNodo(actividadUltimaAgregada);
        Actividad actividad = new Actividad(nombre, descripcion, esObligatoria, iDProceso);
        if(validarActivid(actividad)) listaActividades.agregar(actividad, posicion+1);
        else System.out.println("Actividad repetida");
    }

    public Boolean validarActivid(Actividad actividad){

        for(int i=1; i < listaActividades.getTamanio()+1; i++){
            if(listaActividades.obtener(i).getNombre().equals(actividad.getNombre())){
                return false;
            }
        }
        return true;
    }
}

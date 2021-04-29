package modelo;

import listas.ListaDoble;

public class Proyecto {

    ListaDoble<Proceso> miListaDoble = new ListaDoble();


    public void crearProceso(String nombreProceso,int idProceso) {

        Proceso procesoNuevo= new Proceso(nombreProceso,idProceso);


    }
}

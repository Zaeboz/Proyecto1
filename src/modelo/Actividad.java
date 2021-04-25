package modelo;


import exeptions.TareasNoObligatoriasException;
import listas.Cola;


public class Actividad implements Cloneable {

    private String nombre;
    private String descripcion;
<<<<<<< HEAD
    private boolean esObligatorio;
=======
    private boolean esObligatoria;
>>>>>>> 37b1e5272c0457f72f55a52508efa8364fc74c01
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();
    private int codigoProceso;

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
<<<<<<< HEAD




=======
>>>>>>> 37b1e5272c0457f72f55a52508efa8364fc74c01
    
    public Actividad(String nombre, String descripcion, boolean esObligatorio){
        this.nombre = nombre;
        this.descripcion = descripcion;
<<<<<<< HEAD
        this.esObligatorio = esObligatorio;


    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;

=======
        this.esObligatoria = esObligatorio;
        this.codigoProceso = codigoProceso;
>>>>>>> 37b1e5272c0457f72f55a52508efa8364fc74c01
    }


    public Actividad() {
    }

    public String getNombre(){
        return this.nombre;
    }
<<<<<<< HEAD



=======
>>>>>>> 37b1e5272c0457f72f55a52508efa8364fc74c01
}

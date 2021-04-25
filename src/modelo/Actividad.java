package modelo;


import exeptions.TareasNoObligatoriasException;
import listas.Cola;


public class Actividad implements Cloneable {

    private String nombre;
    private String descripcion;
<<<<<<< HEAD
    private boolean esObligatoria;
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();

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


=======
    private boolean esObligatorio;
    private Cola<Tarea> colaDeTareas = new Cola<Tarea>();
    private int codigoProceso;
    private int codigo;
    
    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigoProceso){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;
        this.codigoProceso = codigoProceso;
        codigo++;
    }

    public Actividad(String nombre, String descripcion, boolean esObligatorio, int codigo,  int codigoProceso){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esObligatorio = esObligatorio;
        this.codigoProceso = codigoProceso;
        this.codigo = codigo;
    }


    public Actividad() {
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
>>>>>>> origin/main
}

package vistas;

import application.Main;
import exeptions.TareasNoObligatoriasException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import listas.Cola;
import listas.ListaDoble;
import listas.ListaSimple;
import modelo.Actividad;
import modelo.Proceso;
import modelo.Tarea;
import persistencia.Persistencia;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControladorTarea implements Initializable {

    @FXML MenuButton botonActividades;
    @FXML MenuItem InsertarFinal;
    @FXML MenuItem InsertarEnPos;

    @FXML  AnchorPane anchorPaneProcesos;
    @FXML  TextField textFiledBuscar;
    @FXML  Button botonBuscarTareas;
    @FXML  MenuItem botonInsertarFinal;
    @FXML  MenuItem botonInsertarEnPos;
    @FXML  Button botonEliminarTarea;
    @FXML  Button botonEditarTarea;

    @FXML TableView<Tarea> tablaTareas = new TableView<>();
    @FXML TableColumn<Tarea, String> nombreActividadColumna = new TableColumn<>("nombreActividad");
    @FXML TableColumn<Tarea, String> nombreTareaColumna = new TableColumn<>("nombre");
    @FXML TableColumn<Tarea, String> descripcionTareaColumna = new TableColumn<>("descripcion");
    @FXML TableColumn<Tarea, Double> tiempoMinColumna = new TableColumn<>("tiempoMinimo");
    @FXML TableColumn<Tarea, Double> tiempoMaxColumna = new TableColumn<>("tiempoMaximo");
    @FXML TableColumn<Tarea, Boolean> obligatoriaColumna = new TableColumn<>("obligatoria");
    ObservableList<Tarea> colaTareas = FXCollections.observableArrayList();
    private String nombreStage;
    private int posicionEnTabla;

    public ControladorTarea() {
    }

    private void llenarTabla()
    {
        colaTareas.removeAll();
        colaTareas.clear();
        ListaSimple<Proceso> procesos = Main.proyecto.getListaProcesos();
        ListaDoble<Actividad> actividades;
        Cola<Tarea> tareas;

        try {
            for (int i = 0; i < procesos.getTamanio(); i++) {
                actividades = procesos.obtenerValorNodo(i).getListaActividades();
                for (int j = 0; j < actividades.getTamanio(); j++) {
                    tareas = actividades.obtenerValorNodo(j).getColaDeTareas();
                    for (int k = 0; k < tareas.getTamano(); k++) {
                        Tarea t = tareas.desencolar();
                        if (t != null) {
                            colaTareas.add(t);
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para lanzar la venta de creacion para crear un actividad al final.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarInsertarFinal(ActionEvent event) throws IOException{
        nombreStage = "VistaInsertarFinal";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorInsertarFinal aux = (ControladorInsertarFinal) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    /**
     * Metodo para lanzar la venta de creacion para crear un actividad al final.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarInsertarPosicion(ActionEvent event) throws IOException{
        nombreStage = "VistaInsertarPosicion";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorVistaInsertarPosicion aux = (ControladorVistaInsertarPosicion) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML void buscarTareas(ActionEvent event) throws IOException {

        nombreStage = "VistaBuscarTareaFormas";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        //ControladorBuscarTareasFormas aux = (ControladorBuscarTareasFormas) loader.getController();
        //aux.conectarControlador(this);
        stage.show();

    }

    private boolean comprobar(String nombre)
    {
        colaTareas.removeAll();
        colaTareas.clear();
        if(nombre.isEmpty())
        {
            llenarTabla();
            return false;
        }
        return true;
    }

    void buscarTareaActividadInicio(String nombre) {

        if(comprobar(nombre)) {
            ListaSimple<Proceso> procesos = Main.proyecto.getListaProcesos();
            ListaDoble<Actividad> actividades;
            Cola<Tarea> tareas;
            for(int p=0; p<procesos.getTamanio(); p++)
            {
                actividades = procesos.obtenerValorNodo(p).getListaActividades();
                for(int a=0; a<actividades.getTamanio(); a++)
                {
                    tareas = actividades.obtenerValorNodo(a).getColaDeTareas();
                    for(int t=0; t<tareas.getTamano(); t++)
                    {
                        if(tareas.desencolar().getNombre().replace(" ", "").toLowerCase().contains(nombre.replace(" ", "").toLowerCase()))
                        {
                            colaTareas.add(tareas.desencolar());
                        }
                    }
                }
            }
        }

    }

    void buscarTareaActividadActual(String nombre) {

        Tarea tarea = getTablaTareaSeleccionada();
        if(comprobar(nombre)) {
            if (tarea == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una tarea para empezar la busqueda.");
            } else {
                String nombreActividadSeleccionada = tarea.getNombreActividad();

                boolean buscando = false;
                ListaSimple<Proceso> procesos = Main.proyecto.getListaProcesos();
                ListaDoble<Actividad> actividades;
                Cola<Tarea> tareas;
                for(int p=0; p<procesos.getTamanio(); p++)
                {
                    actividades = procesos.obtenerValorNodo(p).getListaActividades();
                    for(int a=0; a<actividades.getTamanio(); a++)
                    {
                        if(actividades.obtenerValorNodo(a).getNombre().equalsIgnoreCase(nombreActividadSeleccionada))
                        {
                            buscando = true;
                        }
                        tareas = actividades.obtenerValorNodo(a).getColaDeTareas();
                        for(int t=0; t<tareas.getTamano() && buscando; t++)
                        {
                            if(tareas.desencolar().getNombre().replace(" ", "").toLowerCase().contains(nombre.replace(" ", "").toLowerCase()))
                            {
                                colaTareas.add(tareas.desencolar());
                            }
                        }
                    }
                }
            }
        }

    }

    void buscarTareaActividadNombre(String nombre, String nombreActividad) {

        if(comprobar(nombre)) {
            ListaSimple<Proceso> procesos = Main.proyecto.getListaProcesos();
            ListaDoble<Actividad> actividades;
            Cola<Tarea> tareas;
            for(int p=0; p<procesos.getTamanio(); p++)
            {
                actividades = procesos.obtenerValorNodo(p).getListaActividades();
                for(int a=0; a<actividades.getTamanio(); a++)
                {
                    if(actividades.obtenerValorNodo(a).getNombre().replace(" ", "").equalsIgnoreCase(nombreActividad.replace(" ", ""))) {
                        tareas = actividades.obtenerValorNodo(a).getColaDeTareas();
                        for (int t = 0; t < tareas.getTamano(); t++) {
                            if (tareas.desencolar().getNombre().replace(" ", "").toLowerCase().contains(nombre.replace(" ", "").toLowerCase())) {
                                colaTareas.add(tareas.desencolar());
                            }
                        }
                    }
                }
            }
        }

    }

    @FXML
    public void editar(ActionEvent event) throws CloneNotSupportedException {
        Tarea tareaSeleccionada = getTablaTareaSeleccionada();
        colaTareas.remove(tareaSeleccionada);
        Main.proyecto.eliminarTarea(tareaSeleccionada);
    }

    @FXML
    public void eliminar(ActionEvent event) throws CloneNotSupportedException {
        Tarea tareaSeleccionada = getTablaTareaSeleccionada();
        colaTareas.remove(tareaSeleccionada);
        Main.proyecto.eliminarTarea(tareaSeleccionada);
    }

    private void ponerTareaSeleccionada() {
        final Tarea tarea = getTablaTareaSeleccionada();

        posicionEnTabla = colaTareas.indexOf(tarea);
        if (tarea != null) {
            //Algo falta
        }
    }

    private final ListChangeListener<Tarea> selectorTablaTareas = new ListChangeListener<Tarea>()
    {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Tarea> c)
        {
            ponerTareaSeleccionada();
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.inicilizarTablaTarea();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ObservableList<Tarea> tablaActividadSel = tablaTareas.getSelectionModel().getSelectedItems();
        cargarTablaTareas();
        tablaActividadSel.addListener(selectorTablaTareas);
    }

    private void cargarTablaTareas() {
        ListaSimple<Proceso> listaProcesos = Persistencia.cargarRecursoProyectoXML().getListaProcesos();
        int tamanio = listaProcesos.getTamanio();
        int sizeCola;

        for (int i = 0; i < tamanio; i++) {
            Proceso procesoAux = listaProcesos.obtenerValorNodo(i);
            for (int j = 0; j < procesoAux.getListaActividades().getTamanio(); j++){
                Actividad nuevaActividad = procesoAux.getListaActividades().obtener(j);
                sizeCola = nuevaActividad.getColaDeTareas().getTamano();
                Cola<Tarea> colaTareasAux = nuevaActividad.getColaDeTareas();
                for(int a = sizeCola-1; a >=0; a--){
                    Tarea nuevaTarea = colaTareasAux.desencolar();
                    colaTareas.add(nuevaTarea);
                }
            }
        }
    }

    /**
     * Metodo para crear una actividad. Pueden haber varios casos de creacion.
     * @param tarea
     */
    public void crear(Tarea tarea) throws TareasNoObligatoriasException, CloneNotSupportedException {
        switch (nombreStage){

            case "VistaInsertarFinal":
                try
                {
                    Main.proyecto.crearTareaFinal(tarea);
                    colaTareas.add(tarea);
                }catch(TareasNoObligatoriasException e)
                {
                   // throw new TareasNoObligatoriasException(e.getMessage());
                }
                Main.save();
                break;
            default: System.out.println("Nombre stage esta vacio");
                break;
        }
    }

    public void crear(Tarea tarea, int posicion)
    {
        switch (nombreStage){
            case "VistaInsertarPosicion":
                Main.proyecto.crearTareaPosicion(tarea, posicion);
                colaTareas.add(tarea);
                Main.save();
                break;
            default: System.out.println("Nombre stage esta vacio");
                break;
        }
    }

    public void inicilizarTablaTarea()throws FileNotFoundException, IOException{
        nombreActividadColumna.setCellValueFactory(new PropertyValueFactory<Tarea, String>("nombreActividad"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<Tarea, String>("nombre"));
        descripcionTareaColumna.setCellValueFactory(new PropertyValueFactory<Tarea, String>("descripcion"));
        tiempoMinColumna.setCellValueFactory(new PropertyValueFactory<Tarea, Double>("tiempoMinimo"));
        tiempoMaxColumna.setCellValueFactory(new PropertyValueFactory<Tarea, Double>("tiempoMaximo"));
        obligatoriaColumna.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("obligatoria"));
        tablaTareas.setItems(colaTareas);
    }

    public Tarea getTablaTareaSeleccionada() {
        if (tablaTareas != null) {
            List <Tarea> tabla = tablaTareas.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Tarea competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

}
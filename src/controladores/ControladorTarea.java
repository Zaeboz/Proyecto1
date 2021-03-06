package controladores;

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
import java.util.ResourceBundle;

public class ControladorTarea implements Initializable {

    @FXML MenuItem InsertarFinal;
    @FXML MenuItem InsertarEnPos;

    @FXML  AnchorPane anchorPaneProcesos;
    @FXML  Button botonBuscarTareas;
    @FXML  Button botonEliminarTarea;
    @FXML  Button botonEditarTarea;

    @FXML TableView<Tarea> tablaTareas = new TableView<>();
    @FXML TableColumn<Tarea, String> nombreActividadColumna = new TableColumn<>("nombreActividad");
    @FXML TableColumn<Tarea, String> nombreTareaColumna = new TableColumn<>("nombre");
    @FXML TableColumn<Tarea, String> descripcionTareaColumna = new TableColumn<>("descripcion");
    @FXML TableColumn<Tarea, Double> tiempoDuracion = new TableColumn<>("tiempoDuracion");
    @FXML TableColumn<Tarea, Boolean> esOpcionalColumna = new TableColumn<>("esOpcional");
    ObservableList<Tarea> colaTareas = FXCollections.observableArrayList();

    private String nombreStage;
    private int posicionEnTabla;
    private Stage stage;

    public ControladorTarea() {
    }

    private void llenarTabla() {
        colaTareas.clear();
        ListaSimple<Proceso> procesos = Main.proyecto.getListaProcesos();
        ListaDoble<Actividad> actividades;
        Cola<Tarea> tareas;
        int sizeCola;

        try {
            for (int i = 0; i < procesos.getTamanio(); i++) {
                actividades = procesos.obtenerValorNodo(i).getListaActividades();
                for (int j = 0; j < actividades.getTamanio(); j++) {
                    tareas = actividades.obtenerValorNodo(j).getColaDeTareas();
                    sizeCola = tareas.getTamano();
                    for (int k = 0; k < sizeCola; k++) {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/"+nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearTareaFinal aux = (ControladorCrearTareaFinal) loader.getController();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/"+nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearTareaPosicion aux = (ControladorCrearTareaPosicion) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML public void cargarVistaEditarTarea(ActionEvent event) throws IOException {
        nombreStage = "VistaEditarTarea";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/"+nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorEditarTarea aux = (ControladorEditarTarea) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML void buscarTareas(ActionEvent event) throws IOException {

        nombreStage = "VistaBuscarTareasFormas";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/"+nombreStage+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorBuscarTareasFormas aux = (ControladorBuscarTareasFormas) loader.getController();
        aux.conectarControlador(this);
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

        try {
            Tarea tareaAux = Main.proyecto.buscarTareaInicio(nombre);
            if(tareaAux!=null){
                String a = tareaAux.toString();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText(a);
                alert.showAndWait();
            }else{
                String a = "La tarea no existe";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROS");
                alert.setContentText(a);
                alert.showAndWait();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
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

        try {
            Tarea tareaAux = Main.proyecto.burcarTareaActividad(nombreActividad, nombre);
            if(tareaAux!=null){
                String a = tareaAux.toString();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText(a);
                alert.showAndWait();
            }else{
                String a = "La tarea no existe";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROS");
                alert.setContentText(a);
                alert.showAndWait();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void editarTarea(Tarea tareaNueva) throws CloneNotSupportedException {
        stage.close();
        Tarea tareaSeleccionada = getTablaTareaSeleccionada();
        Main.proyecto.editarTarea(tareaNueva, tareaSeleccionada.getNombre());
        llenarTabla();
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
        colaTareas.clear();
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

    public void crear(Tarea tarea, int posicion)
    {
        stage.close();
        switch (nombreStage){
            case "VistaInsertarPosicion":
                Main.proyecto.crearTareaPosicion(tarea, posicion);
                colaTareas.add(tarea);
                Main.save();
                cargarTablaTareas();
                break;
            case "VistaInsertarFinal":
                try {
                    Main.proyecto.crearTareaFinal(tarea);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                } catch (TareasNoObligatoriasException e) {
                    e.printStackTrace();
                }
                cargarTablaTareas();
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
        tiempoDuracion.setCellValueFactory(new PropertyValueFactory<Tarea, Double>("TiempoDuracion"));
        esOpcionalColumna.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("esOpcional"));
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
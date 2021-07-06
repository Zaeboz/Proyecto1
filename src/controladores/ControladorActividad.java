package controladores;

import application.Main;
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
import listas.ListaDoble;
import listas.ListaSimple;
import modelo.Actividad;
import modelo.Proceso;
import persistencia.Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorActividad implements Initializable, Serializable {

    private static final long serialVersionUID = 1L;

    @FXML MenuButton botonActividades;
    @FXML MenuItem crearDespues;
    @FXML MenuItem crearDespuesUltima;
    @FXML MenuItem crearFinal;
    @FXML MenuItem intercambiarActividades;
    @FXML MenuItem intercambiarTareas;

    @FXML Button botonBuscarActividades;
    @FXML Button botonEditarActividad;
    @FXML Button botonEliminarActividad;

    @FXML TableView<Actividad> tablaDeActividades = new TableView<>();
    @FXML TableColumn<Actividad, String> nombreColumn = new TableColumn<>("nombre");
    @FXML TableColumn<Actividad, Integer> idProcesoColumn = new TableColumn<>("codigoProceso");
    @FXML TableColumn<Actividad, String> descripcionColumn = new TableColumn<>("descripcion");
    @FXML TableColumn<Actividad, Boolean> esObligatoriaColumn = new TableColumn<>("esObligatoria");
    ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();

    @FXML TextField textFiledBuscar;

    @FXML AnchorPane anchorPane = new AnchorPane();
    private String nombreStage;
    public Stage stage;
    private int posicionEnTabla;
    private int numeroActividad = 1;


    //Lanzar mini ventanas
    /**
     * Metodo para lanzar la venta de creacion para crear un actividad
     * despues de la ultima creada.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarCrearDespuesUltima(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaAgregarFinal.fxml"));
        nombreStage = "VistaAgregarDespuesUltima";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearActividadFinal aux = (ControladorCrearActividadFinal) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    /**
     * Metodo para lanzar la venta de creacion para crear un actividad al final.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarCrearFinal(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaAgregarFinal.fxml"));
        nombreStage = "VistaAgregarFinal";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearActividadFinal aux = (ControladorCrearActividadFinal) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    /**
     * Metodo para lanzar la venta de creacion para crear un actividad despues de una
     * actividad en especifico.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarCrearDespues(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaAgregarDespues.fxml"));
        nombreStage = "VistaAgregarDespues";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearActividadDespues aux = (ControladorCrearActividadDespues) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML
    public void lanzarVistaIntercmbiar(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaInterCambiarActividad.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        nombreStage = "IntercabiarActividad";
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorIntercambiarActividad aux = (ControladorIntercambiarActividad) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML
    public void lanzarVistaIntercmbiarTareas(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaInterCambiarActividad.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        nombreStage = "IntercabiarTareas";
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorIntercambiarActividad aux = (ControladorIntercambiarActividad) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    @FXML
    public void lanzarEditarActividad(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaEditarActividad.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorEditarActividad aux = (ControladorEditarActividad) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    //Metodos
    /**
     * Metodo para crear una actividad. Pueden haber varios casos de creacion.
     * @param actividadAux
     * @param actividadAnterior
     */
    public void crear(Actividad actividadAux, String actividadAnterior){

        switch (nombreStage){

            case "VistaAgregarDespuesUltima":
                stage.close();
                Main.proyecto.crearActividadDespuesUltima(actividadAux);
                cargarTablaActividades();
                break;

            case "VistaAgregarFinal":
                stage.close();
                Main.proyecto.crearActividadFinal(actividadAux);
                cargarTablaActividades();
                break;

            case "VistaAgregarDespues":
                stage.close();
                Main.proyecto.crearActividadDespues(actividadAux, actividadAnterior);
                cargarTablaActividades();
                break;

            default: System.out.println("Nombre stage esta vacio");
                break;
        }
    }

    public void intercambiarActividades(String nombreActividad1, String nombreActividad2) {
        stage.close();
        Actividad actividad1 = new Actividad();
        Actividad actividad2 = new Actividad();
        switch(nombreStage){
            case "IntercabiarTareas":
                actividad1 = Main.proyecto.buscarActividadEnProcesos(nombreActividad1);
                actividad2 = Main.proyecto.buscarActividadEnProcesos(nombreActividad2);
                Main.proyecto.intercambiarActividadesTareas(actividad1, actividad2);
                cargarTablaActividades();
                break;

            case "IntercabiarActividad":
                actividad1 = Main.proyecto.buscarActividadEnProcesos(nombreActividad1);
                actividad2 = Main.proyecto.buscarActividadEnProcesos(nombreActividad2);
                Main.proyecto.intercambiarActividades(actividad1, actividad2);
                cargarTablaActividades();
                break;
        }
    }

    @FXML
    public void lanzarVistaBuscarActividad(ActionEvent event) throws IOException, CloneNotSupportedException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaVerActividadDetalle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorBuscarActividad aux = (ControladorBuscarActividad) loader.getController();
        aux.conectarControlador(this);
        Actividad actividadAMostrar = buscarActividad();
        ListaSimple<Proceso> listaProcesos = Main.proyecto.buscarProcesosEnActividades(textFiledBuscar.getText());
        aux.inicilizarTablaActividad(actividadAMostrar, listaProcesos);
        stage.show();

    }

    public Actividad buscarActividad(){
        String nombre = textFiledBuscar.getText();
        Actividad actividadAux = Main.proyecto.buscarActividadEnProcesos(nombre);
        return actividadAux;
    }

    @FXML
    public void eliminar(ActionEvent event){
        Actividad actividadSeleccionada = getTablaActividadSeleccionada();
        listaActividades.remove(actividadSeleccionada);
        Main.proyecto.eliminarActividad(actividadSeleccionada);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.inicilizarTablaActividad();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ObservableList<Actividad> tablaActividadSel = tablaDeActividades.getSelectionModel().getSelectedItems();
        cargarTablaActividades();
        tablaActividadSel.addListener(selectorTablaActividades);
    }

    private void cargarTablaActividades() {
        listaActividades.clear();
        ListaSimple<Proceso> listaProcesos = Persistencia.cargarRecursoProyectoXML().getListaProcesos();
        int tamanio = listaProcesos.getTamanio();
        for (int i = 0; i < tamanio; i++) {
            ListaDoble<Actividad> listaActividadesAux = listaProcesos.obtenerValorNodo(i).getListaActividades();
            int tamanioListaActividades = listaActividadesAux.getTamanio();
            for (int j = 0; j < tamanioListaActividades; j++){
                Actividad nuevaActividad = listaActividadesAux.obtener(j);
                listaActividades.add(nuevaActividad);
                if(j == tamanio-1) numeroActividad = i;
            }
        }
    }

    public void inicilizarTablaActividad()throws FileNotFoundException, IOException{
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Actividad, String>("nombre"));
        idProcesoColumn.setCellValueFactory(new PropertyValueFactory<Actividad, Integer>("codigoProceso"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Actividad, String>("descripcion"));
        esObligatoriaColumn.setCellValueFactory(new PropertyValueFactory<Actividad, Boolean>("esObligatoria"));
        tablaDeActividades.setItems(listaActividades);
    }

    public Actividad getTablaActividadSeleccionada() {
        if (tablaDeActividades != null) {
            List <Actividad> tabla = tablaDeActividades.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Actividad competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }


    private void ponerActividadSeleccionada() {
        final Actividad actividad = getTablaActividadSeleccionada();

        posicionEnTabla = listaActividades.indexOf(actividad);
        if (actividad != null) {
            //Algo falta
        }
    }

    private final ListChangeListener<Actividad> selectorTablaActividades = new ListChangeListener<Actividad>()
    {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Actividad> c)
        {
            ponerActividadSeleccionada();
        }
    };

    public void setNombreStage(String nombreStage){
        this.nombreStage = nombreStage;
    }

    public String getNombreStage(){
        return nombreStage;
    }

    public void editarDatosActividad(String nombreActividad, Boolean esObligatoria, String descripcion) {
        stage.close();
        Actividad actividadAux = getTablaActividadSeleccionada();
        Main.proyecto.editarActividad(actividadAux, nombreActividad, esObligatoria, descripcion);
        cargarTablaActividades();
    }

    public Stage getStage() {
        return stage;
    }
}

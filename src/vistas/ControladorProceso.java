package vistas;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

import application.Main;

public class ControladorProceso implements Initializable, Serializable{

    private static final long serialVersionUID = 1L;

    @FXML AnchorPane anchorPaneProcesos;

    @FXML Button botonLanzarCrearProceso;
    @FXML Button botonEditarProceso;
    @FXML Button botonEliminarProceso;
    @FXML Button botonBuscarProceso;
    @FXML Button botonConsultarActividades;

    @FXML TableView<Proceso> tablaProcesos = new TableView<>();
    @FXML TableColumn<Proceso, Integer> columnaCodigo = new TableColumn<>("Id");
    @FXML TableColumn<Proceso, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML TableColumn<Proceso, Double> columnaTiempoMinimo = new TableColumn<>("TMinimo");
    @FXML TableColumn<Proceso, Double> columnaTiempoMaximo = new TableColumn<>("TMaximo");
    ObservableList<Proceso> listaProcesos = FXCollections.observableArrayList();

    @FXML private TextField textFieldBuscar = new TextField();
    @FXML private TextField textFieldNombreProceso = new TextField();

    ControladorPrincipal controladorPrincipal;
    private int posicionProcesoEnTabla = 0;
    int numeroProceso = 1;
    public Stage stage;

    @FXML public void editarProceso(ActionEvent event){

        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaEditarProceso.fxml"));

            // Cargo la ventana
            Parent root = loader.load();
            ControladorEditarProceso aux=(ControladorEditarProceso)loader.getController();
            aux.conectarControlador(this);

            // Creo el Scene
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML public void consultarActividades(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaActividadesProceso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorActividadesProceso aux = (ControladorActividadesProceso) loader.getController();
        aux.conectarControlador(this);
        Proceso procesoAMostrar = getTablaProcesoSeleccionado();
        aux.inicilizarTablaActividad(procesoAMostrar);
        stage.show();
    }

    @FXML public void eliminarProceso(){
        Proceso procesoSelecionado = getTablaProcesoSeleccionado();
        listaProcesos.remove(procesoSelecionado);
        Main.proyecto.eliminarProceso(procesoSelecionado);
    }

    @FXML private void lanzarVistaCrearProceso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaCrearProceso.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        ControladorCrearProceso aux = (ControladorCrearProceso) loader.getController();
        aux.conectarControlador(this);
        stage.show();
    }

    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Proceso> selectorTablaProceso = new ListChangeListener<Proceso>() {

        public void onChanged(ListChangeListener.Change<? extends Proceso> c) {
            ponerProcesoSeleccionada();
        }
    };


    /**
     * MÃ©todo para poner en los textFields la tupla que selccionemos
     * @return
     */
    private void ponerProcesoSeleccionada() {
        final Proceso proceso = getTablaProcesoSeleccionado();
        posicionProcesoEnTabla = listaProcesos.indexOf(proceso);

        if (proceso != null) {

            textFieldNombreProceso.setText(proceso.getNombreProceso());
        }
    }

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     *
     */
    public Proceso getTablaProcesoSeleccionado() {
        if (tablaProcesos != null) {
            List<Proceso> tabla = tablaProcesos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Proceso competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public void crearProceso(String nombreProceso){
        stage.close();
        Proceso nuevoProceso = new Proceso(nombreProceso,numeroProceso);
        listaProcesos.add(nuevoProceso);
        Main.proyecto.crearProceso(nuevoProceso);
        numeroProceso++;
    }

    private void inicializarTabla()throws FileNotFoundException, IOException{
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<Proceso, Integer>("idProceso"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Proceso, String>("nombreProceso"));
        columnaTiempoMinimo.setCellValueFactory(new PropertyValueFactory<Proceso, Double>("tiempoMinimo"));
        columnaTiempoMaximo.setCellValueFactory(new PropertyValueFactory<Proceso, Double>("tiempoMaximo"));
        tablaProcesos.setItems(listaProcesos);
        try {
            obtenerDuracionProceso();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void cargarTablaProcesos() {
        int tamanio = Persistencia.cargarRecursoProyectoXML().getListaProcesos().getTamanio();
        for (int i = 0; i < tamanio; i++) {
            Proceso nuevoProceso = Persistencia.cargarRecursoProyectoXML().getListaProcesos().obtenerValorNodo(i);
            listaProcesos.add(nuevoProceso);
            if(i == tamanio-1) numeroProceso = i;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.inicializarTabla();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ObservableList<Proceso> tablaProcesoSel = tablaProcesos.getSelectionModel().getSelectedItems();
        cargarTablaProcesos();
        tablaProcesoSel.addListener(selectorTablaProceso);
    }

    public void agregarDato(String nombreProceso){

        Proceso nuevoProceso = new Proceso(nombreProceso,numeroProceso);

        listaProcesos.add(nuevoProceso);
        Main.proyecto.crearProceso(nuevoProceso);
        numeroProceso++;
    }

    public void editarDatosProceso(String nombreProceso, int ideProceso) {
        stage.close();
        Proceso procesoAMostrar = getTablaProcesoSeleccionado();
        Main.proyecto.editarProceso(procesoAMostrar,nombreProceso,ideProceso);
        int posicionProcesoSelc = 0;
        for (int i = 0; i < listaProcesos.size(); i++) {
            if (listaProcesos.get(i) == procesoAMostrar) {
                posicionProcesoSelc = i;
            }
        }

        procesoAMostrar.setNombreProceso(nombreProceso);
        procesoAMostrar.setIdProceso(ideProceso);

        listaProcesos.set(posicionProcesoSelc, procesoAMostrar);
    }

    public void obtenerDuracionProceso( ) throws CloneNotSupportedException {

        ListaSimple<Proceso> listaProcesosAux=new ListaSimple<>();
        listaProcesosAux = Main.proyecto.getListaProcesos();
        Proceso procesoAux = new Proceso();
        for (int i=0;i<listaProcesosAux.getTamanio();i++)
        {
            procesoAux = listaProcesosAux.obtenerValorNodo(i);
            procesoAux.calcularDuracionProceso();
        }
        Persistencia.guardarRecursoProyectoXML(Main.proyecto);
    }
}
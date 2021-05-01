package vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Actividad;
import modelo.Proceso;

public class ControladorActividad implements Initializable {

    @FXML MenuButton botonActividades;
    @FXML MenuItem crearDespues;
    @FXML MenuItem crearDespuesUltima;
    @FXML MenuItem crearFinal;
    @FXML MenuItem intercambiarActividades;

    @FXML Button botonCrearActividad;
    @FXML Button botonBuscarActividades;
    @FXML Button botonEditarActividad;
    @FXML Button botonEliminarActividad;
    @FXML Button botonConsultarTareas;
    @FXML Button botonCancelar;

    @FXML RadioButton radioButtonSiEs;
    @FXML RadioButton radioButtonNoEs;

    @FXML TableView<Actividad> tablaDeActividades = new TableView<>();
    @FXML TableColumn<Actividad, String> nombreColumn = new TableColumn<>("nombre");
    @FXML TableColumn<Actividad, Integer> idProcesoColumn = new TableColumn<>("codigoProceso");
    @FXML TableColumn<Actividad, String> descripcionColumn = new TableColumn<>("descripcion");
    @FXML TableColumn<Actividad, Boolean> esObligatoriaColumn = new TableColumn<>("esObligatoria");
    ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();

    @FXML TextField textFiledBuscar;
    @FXML TextField textFiledNombre;
    @FXML TextField textFiledIDProceso;
    @FXML TextField textFiledDescripcion;
    @FXML TextField textFiledNombrePos;
    @FXML TextField textFiledNombreActividadAnterior;

    @FXML AnchorPane anchorPane = new AnchorPane();
    @FXML AnchorPane anchorPaneCrear = new AnchorPane();

    private ControladorProceso controladorProceso;
    private Main principal = new Main();
    private String nombreStage;
    private int posicionEnTabla;
    private Stage stage;

    /**
     * Metodo para lanzar la venta de creacion para crear un actividad
     * despues de la ultima creada.
     * @param event
     * @throws IOException
     */
    @FXML
    public void cargarCrearDespuesUltima(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaAgregarFinal.fxml"));
        nombreStage = "VistaAgregarDespuesUltima";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaAgregarFinal.fxml"));
        nombreStage = "VistaAgregarFinal";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaAgregarDespues.fxml"));
        nombreStage = "VistaAgregarDespues";
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
		ControladorCrearActividadDespues aux = (ControladorCrearActividadDespues) loader.getController();
		aux.conectarControlador(this);
		stage.show();
    }

    /**
     * Metodo para crear una actividad. Pueden haber varios casos de creacion.
     * @param actividadAux
     * @param actividadAnterior
     */
    public void crear(Actividad actividadAux, String actividadAnterior){
     
        switch (nombreStage){

            case "VistaAgregarDespuesUltima":
                listaActividades.add(actividadAux);
                principal.getProyecto().crearActividadDespuesUltima(actividadAux);

                //Falta poner el metodo para anviar la actividad a la vista de procesos
            break;

            case "VistaAgregarFinal":
                listaActividades.add(actividadAux);
                principal.getProyecto().crearActividadFinal(actividadAux);
                //Falta poner el metodo para anviar la actividad a la vista de procesos
            break;

            case "VistaAgregarDespues":
                listaActividades.add(actividadAux);
                principal.getProyecto().crearActividadDespues(actividadAux, actividadAnterior);
                //Falta poner el metodo para anviar la actividad a la vista de procesos
            break;

            default: System.out.println("Nombre stage esta vacio");
            break;
        }
    }


    @FXML
    public void buscarActividad(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.inicilizarTablaActividad();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ObservableList<Actividad> tablaActividadSel = tablaDeActividades.getSelectionModel().getSelectedItems();
		tablaActividadSel.addListener(selectorTablaActividades);
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
}

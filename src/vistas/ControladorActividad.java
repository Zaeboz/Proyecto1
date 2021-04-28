package vistas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import application.Principal;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.stage.Stage;
import modelo.Actividad;
import modelo.Proceso;

public class ControladorActividad {

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

    @FXML TableView<Actividad> tablaDeActividades = new TableView();;;
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
    private Principal principal;
    private Proceso proceso = new Proceso();
    private String nombreStage;
    private int posicionEnTabla;
    private Stage stage;

    @FXML
    public void cargarCrearDespuesUltima(ActionEvent event){

        FxmlLoader object = new FxmlLoader();
        Principal.setNombreStage("VistaAgregarDespuesUltima");

        Parent root1 = (Parent) object.getPane(Principal.getNombreStage());
        stage = new Stage();
        stage.setScene(new Scene(root1));  
        
        stage.show();
    }

    @FXML
    public void cargarCrearFinal(ActionEvent event){

        FxmlLoader object = new FxmlLoader();
        Principal.setNombreStage("VistaAgregarFinal");
        Parent root1 = (Parent) object.getPane(Principal.getNombreStage());
        stage = new Stage();
        stage.setScene(new Scene(root1));  
        
        stage.show();
    }

    @FXML
    public void cargarCrearDespues(ActionEvent event){
       
        FxmlLoader object = new FxmlLoader();
        nombreStage = "VistaAgregarDespues";
        Parent root1 = (Parent) object.getPane(nombreStage);
        stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.setTitle(nombreStage); 
        stage.show();
    }

    /**
     * Metodo para crear una actividad, hace uso del nombre de la venta de crear
     * (hay que tener en cuenta que existen 3, una por cada caso), para poder efectuar
     * los diferentes casos que hay para crear una actividad. 
     * @param event
     */
    @FXML
    public void cear(ActionEvent event){
        Boolean esObligatoria=false;
        String nombre = "";
        String descripcion = "";
        String nombreActividadAnterior = "";
        Actividad actividadAux = new Actividad();
        int idProceso = 0;
        switch (Principal.getNombreStage()){

            case "VistaAgregarDespuesUltima":
                if(radioButtonNoEs.isSelected()) esObligatoria = false;
                if(radioButtonSiEs.isSelected()) esObligatoria = true;
                nombre = textFiledNombre.getText();
                descripcion = textFiledDescripcion.getText();
                idProceso = Integer.parseInt(textFiledIDProceso.getText());
                actividadAux.setNombre(nombre);
                actividadAux.setDescripcion(descripcion);
                actividadAux.setCodigoProceso(idProceso);
                listaActividades.add(actividadAux);
                proceso.crearActividadDespuesUltima(nombre, descripcion, esObligatoria, idProceso);
                
            break;

            case "VistaAgregarFinal":
                if(radioButtonNoEs.isSelected()) esObligatoria = false;
                if(radioButtonSiEs.isSelected()) esObligatoria = true;
                nombre = textFiledNombre.getText();
                descripcion = textFiledDescripcion.getText();
                idProceso = Integer.parseInt(textFiledIDProceso.getText());
                proceso.crearActividadFinal(nombre, descripcion, esObligatoria, idProceso);
            break;

            case "VistaAgregarDespues":
                if(radioButtonNoEs.isSelected()) esObligatoria = false;
                if(radioButtonSiEs.isSelected()) esObligatoria = true;
                nombre = textFiledNombre.getText();
                descripcion = textFiledDescripcion.getText();
                idProceso = Integer.parseInt(textFiledIDProceso.getText());
                nombreActividadAnterior = textFiledNombreActividadAnterior.getText();
                proceso.crearActividadDespues(nombre, descripcion, esObligatoria, idProceso ,nombreActividadAnterior);
            break;

            default: System.out.println("Nombre stage esta vacio");
            break;
        }
        if(nombreStage != null){
            actividadAux.setNombre(nombre);
            actividadAux.setDescripcion(descripcion);
            actividadAux.setCodigoProceso(idProceso);
            listaActividades.add(actividadAux);
        }
        
    }


    @FXML
    public void buscarActividad(ActionEvent event) {
    }

    
    public void inicializarComponentes() {

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

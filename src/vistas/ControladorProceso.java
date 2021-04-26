package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorProceso implements Initializable {
	
	@FXML AnchorPane anchorPaneProcesos;

	@FXML Button botonCrearProceso;
	@FXML Button botonLanzarCrearProceso;
	@FXML Button botonEditarProceso;
	@FXML Button botonEliminarProceso;
	@FXML Button botonBuscarProceso;
	@FXML Button botonCancelar;

	@FXML TableView tableViewProcesos;
	@FXML TableColumn columnaCodigo;
	@FXML TableColumn columnaNombre;
	@FXML TableColumn columnaDuracion;
	@FXML TableColumn columnaActividades;

	@FXML TextField textFieldBuscar;
	@FXML TextField textFieldNombreProceso;

	@FXML
	public void lanzarVistaCrearProceso(ActionEvent event){
		FxmlLoader object = new FxmlLoader();
        Parent root1 = (Parent) object.getPane("VistaCrearProceso");
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
	}

	@FXML
	public void crearProceso(ActionEvent event){

	}

	@FXML
	public void buscarProceso(ActionEvent event){

	}

	@FXML
	public void editarProceso(ActionEvent event){

	}

	@FXML
	public void eliminarProceso(ActionEvent event){

	}


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}

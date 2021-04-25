package vistas;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class ControladorPrincipal implements Initializable{
	
	@FXML Button tareas;
	@FXML Button procesos;
	@FXML Button actividades;
	@FXML BorderPane mainPane;
	Main main;
	
	@FXML private void cargarVistaProcesos(ActionEvent event) {
		mainPane.setCenter(null);
		FxmlLoader object = new FxmlLoader();
		AnchorPane view = object.getPane("VistaProceso");
		// Double width = view.getWidth();
		// Double heigth = view.getHeight();
		// Stage stage = (Stage) mainPane.getScene().getWindow();
		// System.out.println(width+" "+heigth);
        // stage.setWidth(width);
        // stage.setHeight(heigth);
		mainPane.setCenter(view);
	}
	
	@FXML private void cargarVistaActividades(ActionEvent event) {
		mainPane.setCenter(null);
		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPane("VistaActividad");
		mainPane.setCenter(view);
	}
	
	@FXML private void cargarVistaTareas(ActionEvent event) {
		mainPane.setCenter(null);
		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPane("VistaTarea");
		mainPane.setCenter(view);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}
	
	

	public ControladorPrincipal() {
		super();
		
	}
}

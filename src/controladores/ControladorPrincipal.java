package controladores;

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
import javafx.stage.Stage;


public class ControladorPrincipal implements Initializable{
	
	@FXML Button tareas;
	@FXML Button procesos;
	@FXML Button actividades;
	@FXML BorderPane mainPane;
	BorderPane pane = new BorderPane();;
	Main main;
	
//	procesos.setOnAction(new EventHandler<ActionEvent>() {
//	    @Override
//	    public void handle(ActionEvent event) {
//	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//	        // OR, if you define btn as @FXML private Button btn.
//	        Stage stage = (Stage) btn.getScene().getWindow();
//	        // these two of them return the same stage
//	        stage.setWidth(new_val);
//	        stage.setHeight(new_val);
//	    }
//	});
	
	@FXML private void cargarVistaProcesos(ActionEvent event) {
		mainPane.setCenter(null);
		FxmlLoader object = new FxmlLoader();
		AnchorPane view = object.getPane("VistaProceso");
		Double width = view.getWidth();
		Double heigth = view.getHeight();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		System.out.println(width+" "+heigth);
        stage.setWidth(width);
        stage.setHeight(heigth);
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
		// TODO Auto-generated method stub
		
	}
	
	

	public ControladorPrincipal() {
		super();
		
	}
}

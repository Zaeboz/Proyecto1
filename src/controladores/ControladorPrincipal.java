package controladores;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import persistencia.Persistencia;

public class ControladorPrincipal implements Initializable, Serializable{

	private static final long serialVersionUID = 1L;
	
	@FXML Button tareas;
	@FXML Button procesos;
	@FXML Button actividades;
	@FXML Button buttonCerrar;
	@FXML BorderPane mainPane;

	Main principal = Main.getInstance();
	ControladorActividad controladorActividad = new ControladorActividad();

	@FXML private void cerrarPrograma(ActionEvent event){
		principal.getPrimaryStage().close();
	}
	
	@FXML private void cargarVistaProcesos(ActionEvent event) throws IOException {
		principal.cargarVistaProcesos(mainPane);
		Main.proyecto = Persistencia.cargarRecursoProyectoXML();
	}
	
	@FXML private void cargarVistaActividades(ActionEvent event) throws IOException {
		principal.cargarVistaActividades(mainPane);
		Main.proyecto = Persistencia.cargarRecursoProyectoXML();
	}
	
	@FXML private void cargarVistaTareas(ActionEvent event) throws IOException {
		principal.cargarVistaTareas(mainPane);
		Main.proyecto = Persistencia.cargarRecursoProyectoXML();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
				
	}

	public ControladorPrincipal() {
		super();
		
	}
}

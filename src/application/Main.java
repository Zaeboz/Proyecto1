package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Proyecto;
import persistencia.Persistencia;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {

	private static Main instance;
	private static Stage primaryStage;
	public static Proyecto proyecto = Persistencia.cargarRecursoProyectoXML();

	BorderPane borderPane;

	public Main() {
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaPrincipal.fxml"));
		Parent root = loader.load();
		loader.getController();
		Scene scene = new Scene(root);
		primaryStage = stage;
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void save()
	{
		Persistencia.guardarRecursoProyectoXML(Main.proyecto);
	}

	//Lanzar las vistas

	public void cargarVistaProcesos(BorderPane mainPane) throws IOException {
		mainPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaProceso.fxml"));
		Parent root = loader.load();
		AnchorPane view = (AnchorPane) root;
		mainPane.setCenter(view);
	}
	public void cargarVistaActividades(BorderPane mainPane) throws IOException{
		mainPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaActividad.fxml"));
		Parent root = loader.load();
		AnchorPane view = (AnchorPane) root;
		mainPane.setCenter(view);
	}

	public void cargarVistaTareas(BorderPane mainPane) throws IOException{
		mainPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaTarea.fxml"));
		Parent root = loader.load();
		AnchorPane view = (AnchorPane) root;
		mainPane.setCenter(view);
	}

	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto){
		Main.proyecto = proyecto;
	}
}

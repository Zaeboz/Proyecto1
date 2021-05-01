package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import modelo.Proyecto;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private static Main instance;
	private static Proyecto proyecto = new Proyecto();
	Stage primaryStage;
	BorderPane borderPane;
	
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
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void cargarVistaProcesos(BorderPane mainPane) throws IOException {

		mainPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaProceso.fxml"));	
		Parent root = loader.load();
		AnchorPane view = (AnchorPane) root;
		mainPane.setCenter(view);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Main() {
	}

	public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

	public static Object getControladorProceso() {
		return null;
	}
}

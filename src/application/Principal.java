package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import vistas.ControladorProceso;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;


public class Principal extends Application {
	
	private static Principal instance;
	public String value;
	Stage primaryStage;
	BorderPane borderPane;
	public static ControladorProceso controladorProceso = new ControladorProceso();
	static String nombreStage;

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
	
	public static void main(String[] args) {
		launch(args);
	}

	public void cargarVistaProcesos(BorderPane mainPane) throws IOException {

		mainPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaProceso.fxml"));	
		Parent root = loader.load();
		AnchorPane view = (AnchorPane) root;
		mainPane.setCenter(view);
		controladorProceso = loader.getController();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

    public static void setNombreStage(String string) {
		nombreStage = string;
    }

    public static String getNombreStage() {
        return nombreStage;
    }

	public Principal() {
	}

	public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal();
        }
        return instance;
    }

	public static Object getControladorProceso() {
		return null;
	}
}

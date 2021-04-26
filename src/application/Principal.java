package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import listas.Cola;
import listas.ListaDoble;
import modelo.Actividad;
import modelo.Proceso;
import modelo.Tarea;


public class Principal extends Application {
    private Proceso proceso;
    Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.proceso = new Proceso();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaPrincipal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

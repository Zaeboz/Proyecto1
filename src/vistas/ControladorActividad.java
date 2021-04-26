package vistas;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Proceso;

public class ControladorActividad implements Initializable{

    @FXML MenuButton botonActividades;
    @FXML MenuItem crearDespues;
    @FXML MenuItem crearDespuesUltima;
    @FXML MenuItem crearFinal;
    @FXML MenuItem intercambiarActividades;

    @FXML Button botonBuscarActividades;
    @FXML Button botonCrearActividades;
    @FXML Button botonCancelar;

    @FXML RadioButton radioButtonSiEs;
    @FXML RadioButton radioButtonNoEs;

    @FXML TableView tablaDeActividades;
    @FXML TableColumn nombreColumn;
    @FXML TableColumn idProcesoColumn;
    @FXML TableColumn descripcionColumn;
    @FXML TableColumn tareasColumn;
    @FXML TableColumn editarColumn;
    @FXML TableColumn eliminarColumn;

    @FXML TextField textFiledBuscar;
    @FXML TextField textFiledNombre;
    @FXML TextField textFiledIDProceso;
    @FXML TextField textFiledDescripcion;
    @FXML TextField textFiledNombrePos;
    @FXML TextField textFiledNombreActividadAnterior;

    @FXML AnchorPane anchorPane = new AnchorPane();
    @FXML AnchorPane anchorPaneCrear = new AnchorPane();

    private ControladorProceso controladorProceso;
    private Proceso proceso;
    private String nombreStage;

    @FXML
    public void cargarCrearDespuesUltima(ActionEvent event){
        FxmlLoader object = new FxmlLoader();
        nombreStage = "VistaAgregarDespuesUltima";
        Parent root1 = (Parent) object.getPane(nombreStage);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    public void cargarCrearFinal(ActionEvent event){

        FxmlLoader object = new FxmlLoader();
        nombreStage = "VistaAgregarFinal";
        Parent root1 = (Parent) object.getPane(nombreStage);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    public void cargarCrearDespues(ActionEvent event){
       
        FxmlLoader object = new FxmlLoader();
        nombreStage = "VistaAgregarDespues";
        Parent root1 = (Parent) object.getPane(nombreStage);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    public void cear(ActionEvent event){
        Boolean esObligatoria=false;
        String nombre, descripcion, nombreActividadAnterior;
        int idProceso;
        switch (nombreStage){

            case "VistaAgregarDespuesUltima":
                if(radioButtonNoEs.isSelected()) esObligatoria = false;
                if(radioButtonSiEs.isSelected()) esObligatoria = true;
                nombre = textFiledNombre.getText();
                descripcion = textFiledDescripcion.getText();
                idProceso = Integer.parseInt(textFiledIDProceso.getText());
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
        }
    }

    @FXML
    public void buscarActividad(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

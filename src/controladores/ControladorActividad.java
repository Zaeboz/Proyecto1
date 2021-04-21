package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Actividad;

public class ControladorActividad implements Initializable{

    @FXML MenuButton botonActividades;
    @FXML MenuItem crearDespues;
    @FXML MenuItem crearDespuesUltima;
    @FXML MenuItem crearFinal;

    @FXML Button botonBuscarActividades;
    @FXML Button botonCrearActividades;
    @FXML Button botonCancelar;

    @FXML RadioButton radioButtonSiEs;
    @FXML RadioButton radioButtonNoEs;

    @FXML TableView tablaDeActividades;
    @FXML TableColumn nombreColumn;
    @FXML TableColumn codigoColumn;
    @FXML TableColumn idProcesoColumn;
    @FXML TableColumn descripcionColumn;
    @FXML TableColumn tareasColumn;
    @FXML TableColumn editarColumn;
    @FXML TableColumn eliminarColumn;

    @FXML TextField textFiledBuscar;
    @FXML TextField textFiledNombre;
    @FXML TextField textFiledCodigoProceso;
    @FXML TextField textFiledDescripcion;

    @FXML AnchorPane anchorPane = new AnchorPane();

    private Actividad actividadAux;
    private String nombreStage;

    @FXML
    public void cargarCrearDespuesUltima(ActionEvent event){
        anchorPane = null;
		FxmlLoader object = new FxmlLoader();
        nombreStage = "VistaCrearDespuesUltima";
		anchorPane = object.getPane(nombreStage);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.show();
    }

    @FXML
    public void cargarCrearFinal(ActionEvent event){
        anchorPane = null;
		FxmlLoader object = new FxmlLoader();
		nombreStage = "VistaCrearFinal";
		anchorPane = object.getPane(nombreStage);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.show();
    }

    @FXML
    public void cargarCrearDespues(ActionEvent event){
        anchorPane = null;
		FxmlLoader object = new FxmlLoader();
		nombreStage = "VistaCrearDespues";
		anchorPane = object.getPane(nombreStage);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.show();
    }

    @FXML
    public void cear(ActionEvent event){
        Boolean esObligatoria;
        String nombre, codigoProceso, descripcion;
        switch (nombreStage){

            case "VistaCrearDespuesUltima":
                if(radioButtonNoEs.isSelected()) esObligatoria = false;
                if(radioButtonSiEs.isSelected()) esObligatoria = true;
                nombre = textFiledNombre.getText();
                descripcion = textFiledDescripcion.getText();
                codigoProceso = textFiledCodigoProceso.getText();
                actividadAux.crearActividad(nombre, descripcion, codigoProceso, esObligatoria);
            break;

            case "VistaCrearFinal":
            break;

            case "VistaCrearDespues":
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

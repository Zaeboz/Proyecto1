package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorActividad implements Initializable{

    @FXML TableView tablaDeActividades;
    @FXML MenuButton botonActividades;
    @FXML Button buscarActividades;
    @FXML TableColumn nombreColumn;
    @FXML TableColumn codigoColumn;
    @FXML TableColumn idProcesoColumn;
    @FXML TableColumn descripcionColumn;
    @FXML TableColumn tareasColumn;
    @FXML TableColumn editarColumn;
    @FXML TableColumn eliminarColumn;

    @FXML
    private void buscarActividad(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

}

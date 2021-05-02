package vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorEditarProceso {

    @FXML
    TextField textNombreProceso=new TextField();
    @FXML
    TextField textIdProceso=new TextField();

    @FXML
    Label nombreProcesoAEditar;
    @FXML
    Button btnguardar;

    Stage stage;

    ControladorProceso controladorProceso=new ControladorProceso();

    public void conectarControlador(ControladorProceso controladorProceso) {

        this.controladorProceso=controladorProceso;
    }
    @FXML
    public void guardarDatos()
    {
        String nombreProceso="";
        nombreProceso=textNombreProceso.getText();

        int ideProceso=0;

        ideProceso=Integer.parseInt(textIdProceso.getText());
        System.out.println(Integer.parseInt(textIdProceso.getText()));

        controladorProceso.editarDatosProceso(nombreProceso,ideProceso);
        
    }
}

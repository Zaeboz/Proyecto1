package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ControladorEditarActividad {

	@FXML TextField textNombreActividad = new TextField();
    @FXML TextField textFieldDescripcion = new TextField();
    @FXML RadioButton radioButtonSiEs = new RadioButton();
    @FXML RadioButton radioButtonNoEs = new RadioButton();

    @FXML Button btnguardar;

    ControladorActividad controladorActividad = new ControladorActividad();

    public void conectarControlador(ControladorActividad controladorActividad) {
        this.controladorActividad = controladorActividad;
    }

    @FXML
    public void guardarDatos() throws CloneNotSupportedException {
        Boolean esObligatoria;
        String nombreProceso=textNombreActividad.getText();
        String descripcion = textFieldDescripcion.getText();
        if(radioButtonSiEs.isSelected()) esObligatoria = true;
        else  esObligatoria = false;

        controladorActividad.editarDatosActividad(nombreProceso, esObligatoria, descripcion);
    }
}

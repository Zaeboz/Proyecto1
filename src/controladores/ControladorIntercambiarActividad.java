package controladores;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorIntercambiarActividad implements Serializable{

    private static final long serialVersionUID = 1L;

    private ControladorActividad controladorActividad;

    @FXML Button botonCancelar;
    @FXML Button bitonIntercambiar;

    @FXML TextField textFieldNombreActivida1;
    @FXML TextField textFieldNombreActivida2;
    @FXML TextField textFieldNombreVentana;

    @FXML public void intercambiarActividades(ActionEvent event){
        String nombreActividad1 = textFieldNombreActivida1.getText();
        String nombreActividad2 = textFieldNombreActivida2.getText();
        controladorActividad.intercambiarActividades(nombreActividad1, nombreActividad2);
    }

	public void conectarControlador(ControladorActividad controladorActividad) {
        this.controladorActividad = controladorActividad;
	}
    
}

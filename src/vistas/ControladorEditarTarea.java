package vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Tarea;

public class ControladorEditarTarea {

    @FXML TextField textFieldNombreTarea = new TextField();
    @FXML TextField textFieldDescripcion = new TextField();
    @FXML TextField textFieldDuracion = new TextField();
    @FXML RadioButton radioButtonEsOpcional = new RadioButton();

    @FXML Button btnguardar;

    ControladorTarea controladorTarea = new ControladorTarea();

    public void conectarControlador(ControladorTarea controladorActividad) {
        this.controladorTarea = controladorActividad;
    }

    @FXML
    public void guardarDatos() throws CloneNotSupportedException {
        Boolean esOpcional;
        String nombreTarea = textFieldNombreTarea.getText();
        String descripcion = textFieldDescripcion.getText();
        double tiempoDuracion = Double.parseDouble(textFieldDuracion.getText());
        if(radioButtonEsOpcional.isSelected()) esOpcional = true;
        else  esOpcional = false;

        Tarea tareaAux = new Tarea();
        tareaAux.setNombre(nombreTarea);
        tareaAux.setEsOpcional(esOpcional);
        tareaAux.setDescripcion(descripcion);
        tareaAux.setTiempoDuracion(tiempoDuracion);

        controladorTarea.editarTarea(tareaAux);
    }
}

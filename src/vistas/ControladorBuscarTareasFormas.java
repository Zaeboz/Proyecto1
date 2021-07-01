package vistas;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Tarea;

import javax.swing.*;
import java.util.Locale;

public class ControladorBuscarTareasFormas {

    @FXML
    RadioButton inicio, actual, porNombre;

    @FXML
    TextField txtNombreTarea;

    private ControladorTarea controladorTarea;

    public void conectarControlador(ControladorTarea aux){
        controladorTarea = aux;
    }

    @FXML
    public void aceptar(ActionEvent event) {
        if(inicio.isSelected())
        {
            String nombre = txtNombreTarea.getText();

            controladorTarea.buscarTareaActividadInicio(nombre);
        }else if(actual.isSelected())
        {
            String nombre = txtNombreTarea.getText();

            controladorTarea.buscarTareaActividadActual(nombre);
        }else if(porNombre.isSelected())
        {
            String nombre = txtNombreTarea.getText();
            String nombreActividad = JOptionPane.showInputDialog("Nombre actividad: ");

            if(nombreActividad.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la actividad");
            }else
            {
                controladorTarea.buscarTareaActividadNombre(nombre, nombreActividad);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una opcion");
        }
    }

}
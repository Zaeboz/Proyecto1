package vistas;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Tarea;

import javax.swing.*;

public class ControladorCrearTareaFinal {
    @FXML
    Button botonCrear;

    @FXML
    RadioButton radioButtonObligatoria;

    @FXML TextField txtNombreActividad;
    @FXML TextField txtNombre;
    @FXML TextField txtDescripcion;
    @FXML TextField txtTiempoDuracion;


    private ControladorTarea controladorTarea;

    public void conectarControlador(ControladorTarea aux){
        controladorTarea = aux;
    }

    @FXML public void crear(ActionEvent event) {
        boolean obligatoria = radioButtonObligatoria.isSelected();
        String nombreActividad = txtNombreActividad.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String tiempoDuracion = txtTiempoDuracion.getText();


        if(nombre.isEmpty() || nombreActividad.isEmpty() || tiempoDuracion.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Por favor ingrese los datos");
        }else
        {
            try
            {
                Tarea tarea = new Tarea(nombreActividad, nombre, descripcion, obligatoria, Double.parseDouble(tiempoDuracion));
                controladorTarea.crear(tarea, 0);
            }catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el tiempo en minutos y solo numeros");
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
package vistas;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Tarea;

import javax.swing.*;

public class ControladorVistaInsertarPosicion {
    @FXML
    Button botonCrear;

    @FXML
    RadioButton radioButtonObligatoria;

    @FXML
    TextField txtNombreActividad;
    @FXML
    TextField txtNombre;
    @FXML TextField txtDescripcion;
    @FXML TextField txtTiempoMaximo;
    @FXML TextField txtTiempoMinimo;
    @FXML TextField txtPosicion;

    private ControladorTarea controladorTarea;

    public void conectarControlador(ControladorTarea aux){
        controladorTarea = aux;
    }

    @FXML public void crear(ActionEvent event) {
        boolean obligatoria = radioButtonObligatoria.isSelected();
        String nombreActividad = txtNombreActividad.getText();
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String tiempoMaximo = txtTiempoMaximo.getText();
        String tiempoMinimo  = txtTiempoMinimo.getText();
        String posicion = txtPosicion.getText();

        if(nombre.isEmpty() || nombreActividad.isEmpty() || tiempoMinimo.isEmpty() || tiempoMaximo.isEmpty() || posicion.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Por favor ingrese los datos");
        }else
        {
            try
            {
                Tarea tarea = new Tarea(nombreActividad, nombre, descripcion, obligatoria, Double.parseDouble(tiempoMinimo), Double.parseDouble(tiempoMaximo));
                controladorTarea.crear(tarea, Integer.parseInt(posicion));
            }catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el tiempo en minutos y la posicion solo numeros");
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
}
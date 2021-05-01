package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Actividad;

public class ControladorCrearActividadFinal {
    @FXML Button botonCrearActividad;
    @FXML Button botonCancelar;

    @FXML RadioButton radioButtonSiEs;
    @FXML RadioButton radioButtonNoEs;

    @FXML TextField textFiledNombre;
    @FXML TextField textFiledIDProceso;
    @FXML TextField textFiledDescripcion;
    @FXML TextField textFiledNombreActividadAnterior;

    private ControladorActividad controladorActividad;

    @FXML public void crear(ActionEvent event){
        Boolean esObligatoria=false;

        Actividad actividadAux = new Actividad();

        if(radioButtonNoEs.isSelected()) esObligatoria = false;
        if(radioButtonSiEs.isSelected()) esObligatoria = true;
        String nombre = textFiledNombre.getText();
        String descripcion = textFiledDescripcion.getText();
        
        int idProceso = Integer.parseInt(textFiledIDProceso.getText());
        
        actividadAux.setNombre(nombre);
        actividadAux.setDescripcion(descripcion);
        actividadAux.setEsObligatoria(esObligatoria);
        actividadAux.setCodigoProceso(idProceso);

        controladorActividad.crear(actividadAux,"");
    }

    public void conectarControlador(ControladorActividad aux){
        controladorActividad = aux;
    }
}
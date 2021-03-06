package controladores;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import modelo.Actividad;

public class ControladorCrearActividadDespues implements Serializable {

    private static final long serialVersionUID = 1L;

    @FXML Button botonCrearActividad;
    @FXML Button botonCancelar;

    @FXML RadioButton radioButtonSiEs;
    @FXML RadioButton radioButtonNoEs;

    @FXML TextField textFiledNombre;
    @FXML TextField textFiledIDProceso;
    @FXML TextField textFiledDescripcion;
    @FXML TextField textFiledNombreActividadAnterior;

    private ControladorActividad controladorActividad;

    @FXML public void cancelar(ActionEvent event){
        controladorActividad.getStage().close();
    }

    @FXML public void crear(ActionEvent event){
        Boolean esObligatoria=false;

        Actividad actividadAux = new Actividad();

        if(radioButtonNoEs.isSelected()) esObligatoria = false;
        if(radioButtonSiEs.isSelected()) esObligatoria = true;
        String nombre = textFiledNombre.getText();
        String descripcion = textFiledDescripcion.getText();
        int idProceso = Integer.parseInt(textFiledIDProceso.getText());
        String nombreActividadAnterior = textFiledNombreActividadAnterior.getText();

        actividadAux.setNombre(nombre);
        actividadAux.setDescripcion(descripcion);
        actividadAux.setEsObligatoria(esObligatoria);
        actividadAux.setCodigoProceso(idProceso);

        controladorActividad.crear(actividadAux, nombreActividadAnterior);
    }

    public void conectarControlador(ControladorActividad aux){
        controladorActividad = aux;
    }
}
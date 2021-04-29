package vistas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControladorCrearProceso {
    @FXML private TextField textFieldNombreProceso = new TextField();
    @FXML Button botonCrearProceso;
    @FXML Button botonCancelar;

    private ControladorProceso controladorProceso;

    @FXML public void crearProceso(ActionEvent event)
    {
        System.out.println("Creo proceso");
        String nombreProceso = textFieldNombreProceso.getText();
        controladorProceso.agregarDato(nombreProceso);
    }   

    public void conectarControlador(ControladorProceso aux){
        controladorProceso = aux;
    }
}

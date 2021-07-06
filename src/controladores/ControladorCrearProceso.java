package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class ControladorCrearProceso {
    @FXML private TextField textFieldNombreProceso = new TextField();
    @FXML Button botonCrearProceso;
    @FXML Button botonCancelar;

    private ControladorProceso controladorProceso;

    @FXML public void cancelarCreacion(ActionEvent event){
        controladorProceso.getStage().close();
    }

    @FXML public void crearProceso(ActionEvent event)
    {
        System.out.println("Creo proceso");

        String nombreProceso = textFieldNombreProceso.getText();

        if(!nombreProceso.isEmpty()) {

            controladorProceso.agregarDato(nombreProceso);

        }else{
            showPopUpMessage(Alert.AlertType.WARNING, "Crear proceso", "Proceso no creado",
                    "No ingreso el nombre del proceso");
        }
    }

    public void showPopUpMessage(Alert.AlertType type, String title, String header, String message) {
        Alert popUp = new Alert(type);
        popUp.setTitle(title);
        popUp.setHeaderText(header);
        popUp.setContentText(message);
        popUp.initStyle(StageStyle.DECORATED);
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setWidth(300);
        popUp.setHeight(200);
        popUp.setResizable(false);
        popUp.show();
    }

    public void conectarControlador(ControladorProceso aux){
        controladorProceso = aux;
    }
}
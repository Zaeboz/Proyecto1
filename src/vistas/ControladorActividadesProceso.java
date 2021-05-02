package vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import listas.ListaDoble;

import modelo.Actividad;
import modelo.Proceso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;


public class ControladorActividadesProceso implements Serializable{

    private static final long serialVersionUID = 1L;

    @FXML TableView<Actividad> tablaDeActividades = new TableView<>();
    @FXML TableColumn<Actividad, String> nombreColumn = new TableColumn<>("nombre");
    @FXML TableColumn<Actividad, Integer> idProcesoColumn = new TableColumn<>("codigoProceso");
    @FXML TableColumn<Actividad, String> descripcionColumn = new TableColumn<>("descripcion");
    @FXML TableColumn<Actividad, Boolean> esObligatoriaColumn = new TableColumn<>("esObligatoria");

    @FXML Label labelTablaActividades = new Label();

    ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
    ControladorProceso controladorProceso = new ControladorProceso();

    public void inicilizarTablaActividad(Proceso procesoAMostrar) throws FileNotFoundException, IOException {
        labelTablaActividades.setText("Tabla de actividades del proceso "+procesoAMostrar.getNombreProceso());

        nombreColumn.setCellValueFactory(new PropertyValueFactory<Actividad, String>("nombre"));
        idProcesoColumn.setCellValueFactory(new PropertyValueFactory<Actividad, Integer>("codigoProceso"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Actividad, String>("descripcion"));
        esObligatoriaColumn.setCellValueFactory(new PropertyValueFactory<Actividad, Boolean>("esObligatoria"));

        ListaDoble<Actividad> listaActividadesDelProcesoAMostrar = procesoAMostrar.getListaActividades();


        for (int i = 0; i < listaActividadesDelProcesoAMostrar.getTamanio(); i++) {
            listaActividades.add(listaActividadesDelProcesoAMostrar.obtenerValorNodo(i));
        }

        tablaDeActividades.setItems(listaActividades);
    }

    public void conectarControlador(ControladorProceso controladorProceso) {
        this.controladorProceso = controladorProceso;
    }


}
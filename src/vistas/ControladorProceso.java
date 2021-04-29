package vistas;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Proyecto;
import modelo.Proceso;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorProceso implements Initializable{
    Proyecto libreta = new Proyecto();
    @FXML AnchorPane anchorPaneProcesos;

    @FXML Button botonLanzarCrearProceso;
    @FXML Button botonEditarProceso;
    @FXML Button botonEliminarProceso;
    @FXML Button botonBuscarProceso;

    @FXML TableView<Proceso> tablaProcesos = new TableView<>();
    @FXML TableColumn<Proceso, Integer> columnaCodigo = new TableColumn<>("Id");
    @FXML TableColumn<Proceso, String> columnaNombre = new TableColumn<>("Nombre");
    @FXML TableColumn<Proceso, Double> columnaTiempoMinimo = new TableColumn<>("TMinimo");
    @FXML TableColumn<Proceso, Double> columnaTiempoMaximo = new TableColumn<>("TMaximo");
	ObservableList<Proceso> listaProcesos = FXCollections.observableArrayList();

    @FXML private TextField textFieldBuscar;
    @FXML private TextField textFieldNombreProceso=new TextField();

	ControladorPrincipal controladorPrincipal;
    private int posicionProcesoEnTabla = 0;
    int numeroProceso=0;

	
    @FXML public void editarProceso(){

    }

    @FXML public void eliminarProceso(){

    }

    @FXML public void buscarProceso(){

    }

    @FXML private void lanzarVistaCrearProceso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaCrearProceso.fxml"));

		System.out.println("entre");
		System.out.println(loader==null);
        // Cargo la ventana
        Parent root = loader.load();
	
        // Creo el Scene
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
		ControladorCrearProceso aux = (ControladorCrearProceso) loader.getController();
		aux.conectarControlador(this);
		stage.show();
    }

    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<Proceso> selectorTablaProceso = new ListChangeListener<Proceso>() {

                public void onChanged(ListChangeListener.Change<? extends Proceso> c) {
                    ponerProcesoSeleccionada();
                }
            };


    /**
     * MÃ©todo para poner en los textFields la tupla que selccionemos
     * @return
     */
    private void ponerProcesoSeleccionada() {
        final Proceso proceso = getTablaProcesoSeleccionado();
        posicionProcesoEnTabla = listaProcesos.indexOf(proceso);

        if (proceso != null) {

            textFieldNombreProceso.setText(proceso.getNombreProceso());
        }
    }
	
     /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
	 *          
	 */
    public Proceso getTablaProcesoSeleccionado() {
        if (tablaProcesos != null) {
			List<Proceso> tabla = tablaProcesos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Proceso competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
	}
	public void recibirProceso(Proceso nuevoProceso) {
		listaProcesos.add(nuevoProceso);
	}

	public void agregarDato(String nombreProceso){
		System.out.println("Creo proceso");
        Proceso nuevoProceso = new Proceso(nombreProceso,numeroProceso=numeroProceso+1,30.0,40.0);
		listaProcesos.add(nuevoProceso);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnaCodigo.setCellValueFactory(new PropertyValueFactory<Proceso, Integer>("idProceso"));
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Proceso, String>("nombreProceso"));
		columnaTiempoMinimo.setCellValueFactory(new PropertyValueFactory<Proceso, Double>("tiempoMinimo"));
		columnaTiempoMaximo.setCellValueFactory(new PropertyValueFactory<Proceso, Double>("tiempoMaximo"));
		tablaProcesos.setItems(listaProcesos);

		System.out.println("Haga");

		final ObservableList<Proceso> tablaProcesoSel = tablaProcesos.getSelectionModel().getSelectedItems();
		tablaProcesoSel.addListener(selectorTablaProceso);
	}
}
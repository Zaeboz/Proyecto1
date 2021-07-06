package vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import listas.Cola;
import listas.ListaSimple;
import modelo.Actividad;
import modelo.Proceso;
import modelo.Tarea;

import java.io.Serializable;

public class ControladorBuscarActividad implements Serializable {
    private static final long serialVersionUID = 1L;

    @FXML TableView<Tarea> tablaDeTareas = new TableView<>();
    @FXML TableColumn<Tarea, String> nombreColumn = new TableColumn<>("nombre");
    @FXML TableColumn<Tarea, String> descripcionColumn = new TableColumn<>("descripcion");
    @FXML TableColumn<Tarea, Double> tiempoDuracionColumn = new TableColumn<>("tiempoDuracion");
    @FXML TableColumn<Tarea, Boolean> esOpcionalColumn = new TableColumn<>("esOpcional");
    @FXML Label tablaTareasLabel = new Label();
    @FXML Text tiempoMinimoLabel = new Text();
    @FXML Text tiempoMaximoLabel = new Text();
    @FXML TextArea listaProcesosTextArea = new TextArea();

    ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();
    ControladorActividad controladorActividad = new ControladorActividad();

    public void inicilizarTablaActividad(Actividad actividadMostrar, ListaSimple<Proceso> listaProcesos) throws CloneNotSupportedException {
        tablaTareasLabel.setText("Lista de tareas de la actividad "+actividadMostrar.getNombre());

        nombreColumn.setCellValueFactory(new PropertyValueFactory<Tarea, String>("nombre"));
        tiempoDuracionColumn.setCellValueFactory(new PropertyValueFactory<Tarea, Double>("tiempoDuracion"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Tarea, String>("descripcion"));
        esOpcionalColumn.setCellValueFactory(new PropertyValueFactory<Tarea, Boolean>("esOpcional"));

        tiempoMaximoLabel.setText(actividadMostrar.getTiempoMaximo()+"");
        tiempoMinimoLabel.setText(actividadMostrar.getTiempoMinimo()+"");

        Cola<Tarea> colaTareasMostrar = (Cola<Tarea>) actividadMostrar.getColaDeTareas().clone();
        int sizeCola = colaTareasMostrar.getTamanio();
        Cola<Tarea> colaAux = new Cola<>();

        for (int i = 0; i < sizeCola; i++) {
            Tarea tareaAux = colaTareasMostrar.desencolar();
            colaAux.encolar(tareaAux);
        }

        for (int i = 0; i < sizeCola; i++) {
            listaTareas.add(colaAux.desencolar());
        }

        for(int i = 0; i < listaProcesos.getTamanio(); i++){
            listaProcesosTextArea.setText(listaProcesos.obtenerValorNodo(i).getNombreProceso()+"\n");
        }

        tablaDeTareas.setItems(listaTareas);
        //controladorActividad.buscarActividad();
    }

    public void conectarControlador(ControladorActividad controladorActividad) {
        this.controladorActividad = controladorActividad;
    }
}

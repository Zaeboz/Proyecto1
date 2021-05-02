package vistas;

import java.io.Serializable;
import java.net.URL;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FxmlLoader implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AnchorPane view;

	public AnchorPane getPane(String nombre) {

		try {
			URL fileUrl = Main.class.getResource("/vistas/"+nombre+".fxml");

			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("No se pudo encontrar el fxml");
			}else {
				new FXMLLoader();
				view = FXMLLoader.load(fileUrl);
				
			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error");
			System.out.println(e);
		}
		return view;
	}

}

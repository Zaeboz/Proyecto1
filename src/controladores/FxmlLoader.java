package controladores;

import java.net.URL;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FxmlLoader {
	private AnchorPane view;

	public AnchorPane getPane(String nombre) {

		try {
			URL fileUrl = Main.class.getResource("/controladores/"+nombre+".fxml");

			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("No se pudo encontrar el fxml");
			}else {
				new FXMLLoader();
				view = FXMLLoader.load(fileUrl);
				System.out.println(view.getWidth());
			}

		} catch (Exception e) {
			e.getMessage();
			System.out.println(e);
		}
		return view;
	}

}

package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import modelo.Proyecto;

public class ArchivoUtil {

	public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

		XMLDecoder decodificadorXML;
		Object objetoXML;

		decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
		objetoXML = decodificadorXML.readObject();
		decodificadorXML.close();
		return objetoXML;

	}

	public static void salvarRecursoSerializadoXML(String rutaArchivo, Proyecto proyecto) throws IOException {

		XMLEncoder codificadorXML;

		codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
		codificadorXML.writeObject(proyecto);
		codificadorXML.close();
	}

}

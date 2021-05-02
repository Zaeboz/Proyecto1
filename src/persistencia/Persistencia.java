package persistencia;

import modelo.Proyecto;

public class Persistencia {

    private static final String RUTA_ARCHIVO_MODELO_PROYECTO_XML = "ArchivoXML.xml";

    public static Proyecto cargarRecursoProyectoXML() {
		
		Proyecto proyecto = new Proyecto();
		
		try {
            if((Proyecto)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PROYECTO_XML)!=null){
                proyecto = (Proyecto)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PROYECTO_XML);
                return proyecto;
            }
            else System.out.println("El XML esta vacio");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proyecto;
	}

	public static void guardarRecursoProyectoXML(Proyecto proyecto) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PROYECTO_XML, proyecto);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}

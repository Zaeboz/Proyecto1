package exeptions;

import java.io.Serializable;

public class TareasNoObligatoriasException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public TareasNoObligatoriasException(String message){
        super(message);
    }
    
}

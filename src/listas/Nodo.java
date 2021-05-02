package listas;

import java.io.Serializable;

/**
 * Clase nodo aplicando Generics
 *
 *
 *
 * **/


public class Nodo<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Nodo<T> siguienteNodo = null;
	private T valorNodo = null;


	/**
	 * Constructor de la clase Nodo
	 * @paramdatoElemento que se guarda en el Nodo
	 */
	public Nodo(T valorNodo) {
		this.valorNodo = valorNodo;
	}

	/**
	 * Constructor de la clase Nodo
	 * @param dato Elemento que se guarda en el Nodo
	 * @param siguiente Enlace al siguiente Nodo
	 */
	public Nodo(T dato, Nodo<T> siguiente) {
		super();
		this.valorNodo = dato;
		this.siguienteNodo = siguiente;
	}

    public Nodo() {

    }


    //Metodos get y set de la clase Nodo

	public Nodo<T> getSiguienteNodo() {
		return siguienteNodo;
	}


	public void setSiguienteNodo(Nodo<T> siguienteNodo) {
		this.siguienteNodo = siguienteNodo;
	}


	public T getValorNodo() {
		return valorNodo;
	}


	public void setValorNodo(T valorNodo) {
		this.valorNodo = valorNodo;
	}
}

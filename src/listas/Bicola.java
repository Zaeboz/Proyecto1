package listas;

import java.io.Serializable;

public class Bicola<T> extends Cola<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	Nodo<T> nodoUltimo = null;
	Nodo<T> nodoPrimero = null;

	public Bicola()
	{

	}

	// inserta por el final de la Bicola
	public void ponerFinal(T dato)
	{
		encolar(dato); // metodo heredado de ColaLista
	}

	// inserta por el frente metodo propio de Bicola
	public void ponerFrente(T dato)
	{
		Nodo<T> nuevoNodo;
		nuevoNodo = new Nodo<T>(dato);
		if (estaVacia())
		{
			nodoUltimo = nuevoNodo;
		}
		nuevoNodo.setSiguienteNodo(nodoPrimero);
		nodoPrimero = nuevoNodo;
	}


	// retira elemento frente de la Bicola
	public T quitarFrente()
	{
		T valor = desencolar();
		return valor; // metodo heredado de ColaLista
	}

	// retira elemento final; metodo propio de Bicola.
	// Es necesario recorrer la bicola para situarse en
	// el nodo anterior al final, para despu�s enlazar.
	public T quitarFinal() throws Exception
	{
		T aux;
		if (!estaVacia())
		{
			if (nodoPrimero == nodoUltimo) // la Bicola dispone de un solo nodo
				aux = desencolar();
			else
			{
				Nodo<T> nodo = nodoPrimero;
				while (nodo.getSiguienteNodo() != nodoUltimo){
//				while (nodo.getSiguienteNodo() != null){
					nodo = nodo.getSiguienteNodo();
				}

				aux = nodo.getSiguienteNodo().getValorNodo();
				// siguiente del nodo anterior se pone a null
				nodo.setSiguienteNodo(null);
				nodoUltimo = nodo;
			}
		}
		else
			throw new Exception("Eliminar de una bicola vac�a");
		return aux;
	}

	public Nodo<T> getNodoUltimo() {
		return nodoUltimo;
	}

	public void setNodoUltimo(Nodo<T> nodoUltimo) {
		this.nodoUltimo = nodoUltimo;
	}

	public Nodo<T> getNodoPrimero() {
		return nodoPrimero;
	}

	public void setNodoPrimero(Nodo<T> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}

}

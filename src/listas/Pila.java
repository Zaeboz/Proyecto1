package listas;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class Pila<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Nodo<T> cima;
	private int tamano;

	/**
	 * Verifica si la Pila est� vac�a
	 * @return true si est� vac�a
	 */
	public boolean estaVacia() {
		return cima==null;
	}

	public Pila(){
		
	}

	/**
	 * Inserta un elemento en la Pila - push
	 * @param dato elemento a guardar en la pila
	 */
	public void push(T dato) {

		Nodo<T> nodo = new Nodo<>(dato);
		nodo.setSiguienteNodo(cima);
		cima = nodo;

		tamano++;
	}

	/**
	 * Retorna y elimina el elemento que est� en la cima de la Pila - pop
	 * @return Elemento de la cima
	 */
	public T pop() {

		if(estaVacia()) {
			throw new RuntimeException("La Pila est� vac�a");
		}

		T dato = cima.getValorNodo();
		cima = cima.getSiguienteNodo();
		tamano--;

		return dato;
	}

	/**
	 * Borra completamente la Pila
	 */
	public void borrarPila() {
		cima = null;
		tamano = 0;
	}

	/**
	 * @return the cima - peek
	 */
	public T obtenerCima() {
		return cima.getValorNodo();
	}

	/**
	 * @return the cima - peek
	 */
	public Nodo<T> getCima() {
		return cima;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamano;
	}

	/**
	 * Agrega una Pila nueva
	 * @param pila nueva Pila
	 */
	public void agregar(Pila<T> pila) {

		Pila<T> clon = pila.clone();
		Pila<T> aux = new Pila<>();

		while(!clon.estaVacia()) {
			aux.push(clon.pop());
		}

		while(!aux.estaVacia()) {
			push(aux.pop());
		}

	}

	/**
	 * Imprime una pila en consola
	 */
	public void imprimir() {
		Nodo<T> aux = cima;
		System.out.println("Los valores de la pila son :");
		while(aux!=null) {
			System.out.print(aux.getValorNodo()+"\t");
			aux = aux.getSiguienteNodo();
		}
		System.out.println();
	}



	@Override
	protected Pila<T> clone(){

		Pila<T> pilaFinal = new Pila<>();
		Nodo<T> nodoCima = null;

		for (Nodo<T> aux = cima; aux!=null; aux = aux.getSiguienteNodo()) {

			Nodo<T> nuevo = new Nodo<>( aux.getValorNodo() );

			if(pilaFinal.estaVacia()) {
				pilaFinal.cima = nuevo;
				nodoCima = nuevo;
			}else {
				pilaFinal.cima.setSiguienteNodo(nuevo);
				pilaFinal.cima = nuevo;
			}
			pilaFinal.tamano++;

		}

		pilaFinal.cima = nodoCima;

		return pilaFinal;
	}

	public void setCima(Nodo<T> cima) {
		this.cima = cima;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

}
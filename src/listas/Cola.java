package listas;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class Cola<T> implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;

	private Nodo<T> nodoPrimero = null;
	private Nodo<T> nodoUltimo = null;
	private int tamanio = 0;


	public Cola(){
		
	}

	/**
	 * Agrega un elemento en la Cola
	 * @param dato elemento a guardar en la Cola
	 */
	public void encolar(T dato) {

		Nodo<T> nodo = new Nodo<>(dato);

		if(estaVacia()) {
			nodoPrimero = nodoUltimo = nodo;
		}else {
			nodoUltimo.setSiguienteNodo(nodo);
			nodoUltimo = nodo;
		}

		tamanio++;
	}

	public T obtenerUltimoElemento()
	{
		if(nodoUltimo == null)
		{
			return null;
		}
		T dato= nodoUltimo.getValorNodo();

		return dato;
	}

	/**
	 * Retorna y elimina el elemento que est� al incio de la Cola
	 * @return Primer elemento de la Cola
	 */
	public T desencolar() {

		if(estaVacia()) {
			throw new RuntimeException("La Cola est� vac�a");
		}

		T dato = nodoPrimero.getValorNodo();
		nodoPrimero = nodoPrimero.getSiguienteNodo();

		if(nodoPrimero==null) {
			nodoUltimo = null;
		}

		tamanio--;
		return dato;
	}

	public void insertarElemento(T dato , int posicion)
	{
		Nodo<T> nodoAInsertar = new Nodo<>(dato);

		Nodo<T> nodoAuxiliarActual = new Nodo<>();

		Nodo<T> nodoSiguiente = new Nodo<>();
		if(validarPosicion(posicion)) {
			if (estaVacia()) {

				nodoPrimero = nodoUltimo = nodoAInsertar;
			} else if (posicion == 0) {
				nodoAInsertar.setSiguienteNodo(nodoPrimero);
				nodoPrimero = nodoAInsertar;
			} else {
				for (int i = 0; i < tamanio - 1; i++) {
					nodoAuxiliarActual = nodoAuxiliarActual.getSiguienteNodo();
				}
				nodoSiguiente = nodoAuxiliarActual.getSiguienteNodo();
				nodoAuxiliarActual.setSiguienteNodo(nodoAInsertar);
				nodoAInsertar.setSiguienteNodo(nodoSiguiente);
			}
			tamanio++;
		}
	}

	public boolean validarPosicion(int posicion)
	{
		if(posicion>=0 && posicion<=tamanio)
		{
			return true;
		}

		return false;
	}

	/**
	 * Imprime una cola en consola
	 */
	public void imprimir() {
		Nodo<T> aux = nodoPrimero;
		System.out.println("Los valores de la cola son :");
		while(aux!=null) {
			System.out.print(aux.getValorNodo()+" \t");
			aux = aux.getSiguienteNodo();
		}
		System.out.println();
	}

	/**
	 * Verifica si la Cola est� vac�a
	 * @return true si est� vac�a
	 */
	public boolean estaVacia() {
		return nodoPrimero == null;
	}

	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	/**
	 * Borra completamente la Cola
	 */
	public void borrarCola() {
		nodoPrimero = nodoUltimo = null;
		tamanio = 0;
	}

	/**
	 * @return the primero
	 */
	public Nodo<T> getPrimero() {
		return nodoPrimero;
	}

	/**
	 * @return the ultimo
	 */
	public Nodo<T> getUltimo() {
		return nodoUltimo;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamanio;
	}

	public Nodo<T> getNodoPrimero() {
		return nodoPrimero;
	}

	public void setNodoPrimero(Nodo<T> nodoPrimero) {
		this.nodoPrimero = nodoPrimero;
	}

	public Nodo<T> getNodoUltimo() {
		return nodoUltimo;
	}

	public void setNodoUltimo(Nodo<T> nodoUltimo) {
		this.nodoUltimo = nodoUltimo;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	

}
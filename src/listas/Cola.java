package listas;



/**
 *
 * @param <T>
 */
public class Cola<T> {

	public Nodo<T> nodoPrimero, nodoUltimo;
	public int tamanio;



	
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




}
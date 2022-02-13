package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private int capacidad;
	private int tamano;
	Aula[] coleccionAulas;

	public Aulas(int capacidadColeccion) {
		if (capacidadColeccion <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidadColeccion;
		coleccionAulas = new Aula[capacidadColeccion];
		this.tamano = 0;
	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copiaProfundaAulas = new Aula[getTamano()];
		for (int i = 0; i < copiaProfundaAulas.length; i++) {
			copiaProfundaAulas[i] = coleccionAulas[i];
		}
		return copiaProfundaAulas;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public int getTamano() {
		return this.tamano;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	private int buscarIndice(Aula aula) {
		int indice = 0;
		boolean aulaEncontrada = false;
		while (!tamanoSuperado(indice) && !aulaEncontrada) {
			if (coleccionAulas[indice].equals(aula)) {
				aulaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		} else if (!tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		} else {
			coleccionAulas[indice] = new Aula(aula);
			this.tamano++;
		}
	}

	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}
		int indice = buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Aula(coleccionAulas[indice]);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		{
			int i;
			for (i = indice; !tamanoSuperado(i); i++) {
				coleccionAulas[i] = coleccionAulas[i + 1];
			}
			coleccionAulas[i] = null;
			tamano--;
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}
		if (tamanoSuperado(buscarIndice(aula))) {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(aula));
		}
	}

	public Aula[] get() {
		return copiaProfundaAulas();
	}

	public String[] representar() {
		String[] arrayString = new String[getTamano()];
		for (int i = 0; i < arrayString.length; i++) {
			arrayString[i] = coleccionAulas[i].toString();
		}
		return arrayString;
	}

}

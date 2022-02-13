package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	private int capacidad;
	private int tamano;
	Profesor[] coleccionProfesores;

	public Profesores(int capacidadColeccion) {
		if (capacidadColeccion <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidadColeccion;
		coleccionProfesores = new Profesor[capacidadColeccion];
		this.tamano = 0;
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfundaProfesores = new Profesor[getTamano()];
		for (int i = 0; i < copiaProfundaProfesores.length; i++) {
			copiaProfundaProfesores[i] = coleccionProfesores[i];
		}
		return copiaProfundaProfesores;
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

	private int buscarIndice(Profesor profesor) {
		int indice = 0;
		boolean profesorEncontrado = false;
		while (!tamanoSuperado(indice) && !profesorEncontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				profesorEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		} else if (!tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		} else {
			coleccionProfesores[indice] = new Profesor(profesor);
			this.tamano++;
		}
	}

	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}
		int indice = buscarIndice(profesor);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			return new Profesor(coleccionProfesores[indice]);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		{
			int i;
			for (i = indice; !tamanoSuperado(i); i++) {
				coleccionProfesores[i] = coleccionProfesores[i + 1];
			}
			coleccionProfesores[i] = null;
			tamano--;
		}
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}
		if (tamanoSuperado(buscarIndice(profesor))) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(profesor));
		}
	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	public String[] representar() {
		String[] arrayString = new String[getTamano()];
		for (int i = 0; i < arrayString.length; i++) {
			arrayString[i] = coleccionProfesores[i].toString();
		}
		return arrayString;
	}

}

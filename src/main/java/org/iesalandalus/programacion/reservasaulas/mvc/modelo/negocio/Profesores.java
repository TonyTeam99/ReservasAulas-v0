package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

public class Profesores {
	private int capacidad;
	private int tamano;
	private Profesor[] coleccionProfesores;

	public Profesores(int tamano) {
		if (tamano <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.coleccionProfesores = new Profesor[tamano];
		}
	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copia = new Profesor[coleccionProfesores.length];

		for (int i = 0; i < coleccionProfesores.length; i++) {
			copia[i] = coleccionProfesores[i];
		}
		return copia;
	}

	public int getCapacidad() {
		return coleccionProfesores.length;
	}

	public int getTamano() {
		tamano = 0;
		for (int i = 0; i < coleccionProfesores.length; i++) {
			if (coleccionProfesores[i] != null) {
				tamano = tamano + 1;
			}
		}
		return tamano;
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		} else {
			if (tamanoSuperado(getTamano()) == true) {
				throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
			} else {
				if (buscarIndice(profesor) != -1) {
					throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");
				} else {
					coleccionProfesores[getTamano()] = new Profesor(profesor);
				}
			}

		}

	}

	private int buscarIndice(Profesor profesor) {

		return Arrays.asList(coleccionProfesores).indexOf(profesor);
	}

	private boolean tamanoSuperado(int tamano) {

		if (tamano >= getCapacidad()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean capacidadSuperado(int capacidad) {

		if (capacidad > getCapacidad()) {
			return true;
		} else {
			return false;
		}
	}

	public Profesor buscar(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		} else {
			if (buscarIndice(profesor) == -1) {
				return null;
			} else {
				return coleccionProfesores[buscarIndice(profesor)];
			}
		}
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		} else {

			if (buscarIndice(profesor) == -1) {
				throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");
			} else {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(profesor));
			}
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; i < getTamano(); i++) {
			coleccionProfesores[i] = coleccionProfesores[i + 1];
		}
		coleccionProfesores[getTamano()] = null;
	}

	public String[] representar() {
		String[] representa = new String[getTamano()];
		for (int i = 0; i < representa.length; i++) {
			representa[i] = coleccionProfesores[i].toString();
		}
		return representa;

	}
}
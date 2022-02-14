package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

public class Aulas {
	private int capacidad;
	private int tamano;
	private Aula[] coleccionAulas;

	public Aulas(int tamano) {
		if (tamano <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.coleccionAulas = new Aula[tamano];
		}

	}

	public Aula[] get() {
		return copiaProfundaAulas();
	}

	private Aula[] copiaProfundaAulas() {
		Aula[] copia = new Aula[coleccionAulas.length];

		for (int i = 0; i < coleccionAulas.length; i++) {
			copia[i] = coleccionAulas[i];
		}
		return copia;
	}

	public int getCapacidad() {

		return coleccionAulas.length;
	}

	public int getTamano() {
		tamano = 0;
		for (int i = 0; i < coleccionAulas.length; i++) {
			if (coleccionAulas[i] != null) {
				tamano = tamano + 1;
			}
		}
		return tamano;
	}

	private int buscarIndice(Aula aula) {

		return Arrays.asList(coleccionAulas).indexOf(aula);

	}

	private boolean tamanoSuperado(int tamano) {

		if (tamano >= getCapacidad()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean capacidadSuperada(int capacidad) {

		if (capacidad > getCapacidad()) {
			return true;
		} else {
			return false;
		}
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		} else {
			if (tamanoSuperado(getTamano()) == true) {
				throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
			} else {
				if (buscarIndice(aula) != -1) {
					throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
				} else {
					coleccionAulas[getTamano()] = new Aula(aula);
				}
			}

		}

	}

	public Aula buscar(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		} else {
			if (buscarIndice(aula) == -1) {
				return null;
			} else {
				return coleccionAulas[buscarIndice(aula)];
			}
		}
	}

	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		} else {

			if (buscarIndice(aula) == -1) {
				throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
			} else {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(aula));
			}
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; i < getTamano(); i++) {
			coleccionAulas[i] = coleccionAulas[i + 1];
		}
		coleccionAulas[getTamano()] = null;
	}

	public String[] representar() {
		String[] representa = new String[getTamano()];
		for (int i = 0; i < representa.length; i++) {
			representa[i] = coleccionAulas[i].toString();
		}
		return representa;

	}
}
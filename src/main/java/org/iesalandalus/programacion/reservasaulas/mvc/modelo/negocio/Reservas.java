package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.util.Arrays;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	private int capacidad;
	private int tamano;
	private Reserva[] coleccionReservas;

	public Reservas(int tamano) {
		if (tamano <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		} else {
			this.coleccionReservas = new Reserva[tamano];
		}

	}

	public Reserva[] get() {
		return copiaProfundaAulas();
	}

	private Reserva[] copiaProfundaAulas() {
		Reserva[] copia = new Reserva[coleccionReservas.length];

		for (int i = 0; i < coleccionReservas.length; i++) {
			copia[i] = coleccionReservas[i];
		}
		return copia;
	}

	public int getCapacidad() {
		return coleccionReservas.length;
	}

	public int getTamano() {
		tamano = 0;
		for (int i = 0; i < coleccionReservas.length; i++) {
			if (coleccionReservas[i] != null) {
				tamano = tamano + 1;
			}
		}
		return tamano;
	}

	private int buscarIndice(Reserva reserva) {

		return Arrays.asList(coleccionReservas).indexOf(reserva);

	}

	private boolean tamanoSuperado(int tamano) {

		if (tamano >= getCapacidad()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean capacidadSuperado(int capacidad) {

		if (tamano > getCapacidad()) {
			return true;
		} else {
			return false;
		}
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		} else {
			if (tamanoSuperado(getTamano()) == true) {
				throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
			} else {
				if (!(buscarIndice(reserva) != -1)) {
					coleccionReservas[getTamano()] = new Reserva(reserva);
				} else {
					throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");

				}
			}

		}
	}

	public Reserva buscar(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		} else {
			if (buscarIndice(reserva) == -1) {
				return null;
			} else {
				return coleccionReservas[buscarIndice(reserva)];
			}
		}
	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		} else {

			if (buscarIndice(reserva) == -1) {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");
			} else {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(reserva));
			}
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
		for (int i = posicion; i < getTamano(); i++) {
			coleccionReservas[i] = coleccionReservas[i + 1];
		}
		coleccionReservas[getTamano()] = null;
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasprofesor = new Reserva[getCapacidad()];
		int indice = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getProfesor() == profesor) {
				reservasprofesor[indice] = coleccionReservas[i];
				indice++;
			}
		}
		return reservasprofesor;
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservaspermanencia = new Reserva[getCapacidad()];
		int indice = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getPermanencia() == permanencia) {
				reservaspermanencia[indice] = coleccionReservas[i];
				indice++;
			}
		}
		return reservaspermanencia;
	}

	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula = new Reserva[getCapacidad()];
		int indice = 0;
		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getAula() == aula) {
				reservasAula[indice] = coleccionReservas[i];
				indice++;
			}
		}
		return reservasAula;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");

		}
		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; i < getTamano(); i++) {
			if (coleccionReservas[i].getAula() == aula) {
				if (coleccionReservas[i].getPermanencia() == permanencia) {

					return false;
				}

			}
		}
		return true;
	}

	public String[] representar() {
		String[] representa = new String[getTamano()];
		for (int i = 0; i < representa.length; i++) {
			representa[i] = coleccionReservas[i].toString();
		}
		return representa;

	}
}
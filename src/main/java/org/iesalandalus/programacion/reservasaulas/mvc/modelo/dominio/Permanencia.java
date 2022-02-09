package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {
	private LocalDate dia;
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Tramo tramo;

	public Permanencia(LocalDate dia, Tramo tramo) {
		setDia(dia);
		setTramo(tramo);
	}

	public Permanencia(Permanencia copiaPermanencia) {
		if (copiaPermanencia == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}
		setDia(copiaPermanencia.getDia());
		setTramo(copiaPermanencia.getTramo());
	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null) {
			throw new NullPointerException("ERROR: El día de una permanencia no puede ser nulo.");
		}
		this.dia = dia;
	}

	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null) {
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		}
		if (tramo == Tramo.MANANA) {
			this.tramo = Tramo.MANANA;
		} else if (tramo == Tramo.TARDE) {
			this.tramo = Tramo.TARDE;
		} else
			throw new IllegalArgumentException("ERROR: Tramo incorrecto");
	}

	@Override
	public int hashCode() {
		return Objects.hash(dia, tramo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Permanencia other = (Permanencia) obj;
		if (dia == null) {
			if (other.dia != null) {
				return false;
			}
		} else if (!dia.equals(other.dia) && !tramo.equals(other.tramo)) {
			return false;
		}
		return tramo == other.tramo;
	}

	@Override
	public String toString() {
		return "dia=" + dia.format(FORMATO_DIA) + ", tramo=" + tramo.toString() + "";
	}

}

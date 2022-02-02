package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
	private String nombre;

	public Aula(String nombre) {
		setNombre(nombre);
	}

	public Aula(Aula copiaAula) {
		if (copiaAula == null) {
			throw new NullPointerException("ERROR: No se puede copiar una permanencia nula.");
		}
		setNombre(copiaAula.getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new NullPointerException("ERROR: La fecha de una permanencia no puede ser nula.");
		}
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
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
		Aula other = (Aula) obj;
		if (nombre.equals(other.nombre)) {
			return false;
		} else
			return nombre.equals(other.nombre);
	}

	@Override
	public String toString() {
		return "Aula [nombre=" + nombre + "]";
	}

}

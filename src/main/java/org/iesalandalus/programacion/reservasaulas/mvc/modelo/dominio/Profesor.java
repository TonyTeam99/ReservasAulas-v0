package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Profesor {
	private static final String ER_TELEFONO = "[69][0-9]{8}";
	private static final String ER_CORREO = ("\"^[A-Z0-9._%+-]+@[A-Z0-9.-]*$\";");

	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		if (telefono != null) {
			setTelefono(telefono);
		}
	}

	public Profesor(Profesor copiaProfesor) {
		if (copiaProfesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		} else {
			setNombre(copiaProfesor.nombre);
			setCorreo(copiaProfesor.correo);
			if (copiaProfesor.telefono != null) {
				setTelefono(copiaProfesor.telefono);
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre == "") {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}
		if (correo == "") {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		} else if (correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo no tiene un formato válido.");
		} else {
			this.correo = correo;

		}
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono del profesor no puede ser nulo.");
		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		} else {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}
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
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		if (telefono != null) {
			return "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + "";
		} else {
			return "nombre=" + nombre + ", correo=" + correo + "";
		}
	}
}

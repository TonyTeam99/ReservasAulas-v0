package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {
	MAÑANA("mañana"), TARDE("tarde");

	private String cadenaAMostrar;

	Tramo(String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar;
	}

	public String toString() {
		return cadenaAMostrar;
	}
}

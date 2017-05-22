package com.programa.model;

public class VacunasCont {

	private String nombre;
	private int edadAplicacion;
	private long fechaAplicada;

	public VacunasCont(String nombre, int edadAplicacion, long fechaAplicada) {
		this.nombre = nombre;
		this.edadAplicacion = edadAplicacion;
		this.fechaAplicada = fechaAplicada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getEdadAplicacion() {
		return edadAplicacion;
	}

	public void setEdadAplicacion(int edadAplicacion) {
		this.edadAplicacion = edadAplicacion;
	}

	public long getFechaAplicada() {
		return fechaAplicada;
	}

	public void setFechaAplicada(long fechaAplicada) {
		this.fechaAplicada = fechaAplicada;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", edadAplicacion=" + edadAplicacion + ", fechaAplicada=" + fechaAplicada;
	}

}

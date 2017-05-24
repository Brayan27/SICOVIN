package com.programa.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		Calendar fechaAplicacion = Calendar.getInstance();
		fechaAplicacion.setTimeInMillis(fechaAplicada);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd ");
		return "nombre=" + nombre + ", edadAplicacion=" + edadAplicacion + ", fechaAplicada="
				+ format1.format(fechaAplicacion.getTime());
	}
	
	public String imprimirPendiente() {
		return "nombre=" + nombre + ", edadAplicacion=" + edadAplicacion;
	}

}

package com.programa.model;

public class CalendarioVacunacion {

	private long idVacuna;
	private int edadAplicacion;
	private String tipo;

	public CalendarioVacunacion(long idVacuna, int edadAplicacion, String tipo) {
		super();
		this.idVacuna = idVacuna;
		this.edadAplicacion = edadAplicacion;
		this.tipo = tipo;
	}

	public CalendarioVacunacion() {
		super();
	}

	public long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(long idVacuna) {
		this.idVacuna = idVacuna;
	}

	public int getEdadAplicacion() {
		return edadAplicacion;
	}

	public void setEdadAplicacion(int edadAplicacion) {
		this.edadAplicacion = edadAplicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

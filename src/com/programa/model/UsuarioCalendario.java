package com.programa.model;

public class UsuarioCalendario {

	private long idUsuario;
	private long idVacuna;
	private int edadAplicacion;
	private long fechaAplicacion;
	
	public UsuarioCalendario(long idUsuario, long idVacuna, int edadAplicacion, long fechaAplicacion) {
		super();
		this.idUsuario = idUsuario;
		this.idVacuna = idVacuna;
		this.edadAplicacion = edadAplicacion;
		this.fechaAplicacion = fechaAplicacion;
	}
	
	public UsuarioCalendario() {
		super();
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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

	public long getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(long fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
}

package com.programa.model;

public class Cita {
	private long idUsuario;
	private long idVacuna;
	private int edadAplicacion;
	private long fechaCita;
	private int se_Aplico;
	public Cita(long idUsuario, long idVacuna, int edadAplicacion, long fechaCita,int se_Aplico) {
		super();
		this.idUsuario = idUsuario;
		this.idVacuna = idVacuna;
		this.edadAplicacion = edadAplicacion;
		this.fechaCita = fechaCita;
		this.se_Aplico = se_Aplico;
	}
	
	public Cita() {
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

	public long getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(long fechaCita) {
		this.fechaCita = fechaCita;
	}

	public int getSe_Aplico() {
		return se_Aplico;
	}

	public void setSe_Aplico(int se_Aplico) {
		this.se_Aplico = se_Aplico;
	}
	
}

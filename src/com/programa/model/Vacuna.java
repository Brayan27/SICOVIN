package com.programa.model;

public class Vacuna {

	private long idVacuna;
	private String nombreVacuna;

	public Vacuna(long idVacuna, String nombreVacuna) {
		super();
		this.idVacuna = idVacuna;
		this.nombreVacuna = nombreVacuna;
	}

	public Vacuna() {
	}

	public long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(long idVacuna) {
		this.idVacuna = idVacuna;
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
}

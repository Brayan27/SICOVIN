package com.programa.model;

public class Distrito {

	private int canton;
	private int distrito;
	private String nombreDistrito;

	public Distrito(int canton, int distrito, String nombreDistrito) {
		super();
		this.canton = canton;
		this.distrito = distrito;
		this.nombreDistrito = nombreDistrito;
	}

	public int getCanton() {
		return canton;
	}

	public void setCanton(int canton) {
		this.canton = canton;
	}

	public int getDistrito() {
		return distrito;
	}

	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}

	public String getNombreDistrito() {
		return nombreDistrito;
	}

	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}

}

package com.programa.model;

public class Canton {

	private int provincia;
	private int canton;
	private String nombreCanton;

	public Canton(int provincia, int canton, String nombreCanton) {
		this.provincia = provincia;
		this.canton = canton;
		this.nombreCanton = nombreCanton;
	}

	public int getProvincia() {
		return provincia;
	}

	public void setProvincia(int provincia) {
		this.provincia = provincia;
	}

	public int getCanton() {
		return canton;
	}

	public void setCanton(int canton) {
		this.canton = canton;
	}

	public String getNombreCanton() {
		return nombreCanton;
	}

	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}

}

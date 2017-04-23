package com.programa.modelo;

import java.util.ArrayList;

import com.programa.sicovin.RegisterActivity;

public class Globales {
	private String datos;
	private ArrayList<Provincia> provincias;
	private RegisterActivity registerActivity;
	public static Globales global;

	private Globales() {
		provincias = new ArrayList<>();
		datos = "";
	}
	public static Globales obtenerInstancia() {
		return (global == null) ? global = new Globales() : global;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
		System.out.println(datos);
	}

	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
		cargarSpinnerProvincias();
	}

	public RegisterActivity getRegisterActivity() {
		return registerActivity;
	}

	public void setRegisterActivity(RegisterActivity registerActivity) {
		this.registerActivity = registerActivity;
	}

	public void cargarSpinnerProvincias() {
		registerActivity.cargarSpinnerProvincias();
	}
}

package com.programa.model;

import com.programa.controller.Controller;

import android.app.Activity;
import android.widget.Spinner;

public class ClaseUseSpinner extends Activity {

	protected Spinner spinnerProvincias;
	protected Spinner spinnerCantones;
	protected Spinner spinnerDistritos;

	public void cargarProvincias() {
	}

	public void cargarCantones() {

	}

	public void cargarDistritos() {

	}

	protected void ejecutarWebService(String URL, String metodo, String dato) {
		Controller.obtenerInstancia().iniciarWebServices(URL, metodo, dato, this);
	}

}

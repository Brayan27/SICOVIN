package com.programa.controller;

import java.util.ArrayList;

import com.programa.model.Canton;
import com.programa.model.Distrito;
import com.programa.model.Provincia;
import com.programa.model.Usuario;
import com.programa.services.UsuarioService;
import com.programa.sicovin.RegisterActivity;

import android.content.Context;
import android.util.Log;

public class Controller {
	private String datos;
	private ArrayList<Provincia> provincias;
	private ArrayList<Canton> cantones;
	private ArrayList<Distrito> distritos;
	private RegisterActivity registerActivity;
	private UsuarioService usuarioService;
	private Usuario usuario;
	public static Controller global;

	private Controller() {
		provincias = new ArrayList<>();
		cantones = new ArrayList<>();
		distritos = new ArrayList<>();
		datos = "";
	}

	public static Controller obtenerInstancia() {
		return (global == null) ? global = new Controller() : global;
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

	public ArrayList<Canton> getCantones() {
		return cantones;
	}

	public void setCantones(ArrayList<Canton> cantones) {
		this.cantones = cantones;
		cargarSpinnerCantones();
	}

	public ArrayList<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(ArrayList<Distrito> distritos) {
		this.distritos = distritos;
		cargarSpinnerDistritos();
	}

	public RegisterActivity getRegisterActivity() {
		return registerActivity;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setRegisterActivity(RegisterActivity registerActivity) {
		this.registerActivity = registerActivity;
	}

	public void cargarSpinnerProvincias() {
		registerActivity.cargarSpinnerProvincias();
	}

	public void cargarSpinnerCantones() {
		registerActivity.cargarSpinnerCantones();
	}

	public void cargarSpinnerDistritos() {
		registerActivity.cargarSpinnerDistritos();
	}

	public String getIdCanton(String nombreCanton) {
		String idCanton = "0";
		for (Canton canton : cantones) {
			if (canton.getNombreCanton().equals(nombreCanton)) {
				idCanton = String.valueOf(canton.getCanton());
				break;
			}
		}
		return idCanton;
	}

	public void iniciarSesion(Usuario usr, Context context) {
		try {
			usuarioService = new UsuarioService(context);
			usuario = usuarioService.getUsuario(usr);
		} catch (Exception ex) {
			Log.e(null, ex.getMessage());
		}
	}

	public void registrarUsuario(Usuario usr, Context context) {
		usuarioService = new UsuarioService(context);
		usuario = usuarioService.addUsuario(usr);
	}

	public boolean existeUsuario(Usuario usr, Context context) {
		usuarioService = new UsuarioService(context);
		return usuarioService.existeUsuario(usr);
	}
}

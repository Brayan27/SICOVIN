package com.programa.controller;

import java.util.ArrayList;

import com.programa.model.Canton;
import com.programa.model.ClaseUseSpinner;
import com.programa.model.Distrito;
import com.programa.model.Provincia;
import com.programa.model.Usuario;
import com.programa.services.UsuarioService;
import com.programa.services.WebServiceDisCR;

import android.content.Context;
import android.util.Log;

public class Controller {

	private ArrayList<Provincia> provincias;
	private ArrayList<Canton> cantones;
	private ArrayList<Distrito> distritos;
	private ClaseUseSpinner claseUseSpinner;
	private WebServiceDisCR webServiceDisCR;
	private UsuarioService usuarioService;
	private Usuario usuario;
	public static Controller global;

	private Controller() {
		provincias = new ArrayList<>();
		cantones = new ArrayList<>();
		distritos = new ArrayList<>();
	}

	public static Controller obtenerInstancia() {
		return (global == null) ? global = new Controller() : global;
	}

	public ArrayList<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(ArrayList<Provincia> provincias) {
		this.provincias = provincias;
		claseUseSpinner.cargarProvincias();
		// cargarSpinnerProvincias();
	}

	public ArrayList<Canton> getCantones() {
		return cantones;
	}

	public void setCantones(ArrayList<Canton> cantones) {
		this.cantones = cantones;
		claseUseSpinner.cargarCantones();
	}

	public ArrayList<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(ArrayList<Distrito> distritos) {
		this.distritos = distritos;
		claseUseSpinner.cargarDistritos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public int actualizarUsuario(Usuario usr, Context context) {
		int AffectedRow = 0;
		usuarioService = new UsuarioService(context);
		usr.setIdUsuario(usuario.getIdUsuario());
		AffectedRow = usuarioService.updateUsuario(usr);
		usuario = (AffectedRow == 0) ? usuario : usr;
		return AffectedRow;
	}

	public void iniciarWebServices(String URL, String metodo, String dato, ClaseUseSpinner clase) {
		webServiceDisCR = new WebServiceDisCR();
		claseUseSpinner = clase;
		webServiceDisCR.execute(URL, metodo, dato);
	}
}

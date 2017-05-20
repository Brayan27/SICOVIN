package com.programa.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.CipherInputStream;

import com.programa.model.CalendarioVacunacion;
import com.programa.model.Canton;
import com.programa.model.ClaseUseSpinner;
import com.programa.model.Distrito;
import com.programa.model.Provincia;
import com.programa.model.Usuario;
import com.programa.model.UsuarioCalendario;
import com.programa.model.Vacuna;
import com.programa.services.CalendarioVacunacionService;
import com.programa.services.UsuarioCalendarioService;
import com.programa.services.UsuarioService;
import com.programa.services.VacunaService;
import com.programa.services.WebServiceDisCR;

import android.content.Context;
import android.util.Log;

public class Controller {

	private ArrayList<Provincia> provincias;
	private ArrayList<Canton> cantones;
	private ArrayList<Distrito> distritos;
	private ArrayList<Vacuna> vacunas;
	private ArrayList<CalendarioVacunacion> calendarioVacunaciones;
	private ClaseUseSpinner claseUseSpinner;
	private WebServiceDisCR webServiceDisCR;
	private UsuarioService usuarioService;
	private VacunaService vacunaService;
	private CalendarioVacunacionService calendarioVacunacionService;
	private UsuarioCalendarioService usuarioCalendarioService;
	private Usuario usuario;
	public static Controller global;

	private Controller() {
		provincias = new ArrayList<>();
		cantones = new ArrayList<>();
		distritos = new ArrayList<>();
		vacunas = new ArrayList<>();
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

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
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

	public void cargarTablaVacuna(Context context) {
		vacunaService = new VacunaService(context);
		if (!vacunaService.existeTabla()) {
			ArrayList<Vacuna> vacunas = new ArrayList<Vacuna>();
			vacunas.add(new Vacuna(1, "BCG"));
			vacunas.add(new Vacuna(2, "Hepatitis B"));
			vacunas.add(new Vacuna(3, "Haemophilus influenzae"));
			vacunas.add(new Vacuna(4, "DPT"));
			vacunas.add(new Vacuna(5, "Antipolio"));
			vacunas.add(new Vacuna(6, "SRP"));
			vacunas.add(new Vacuna(7, "Varicela"));
			vacunas.add(new Vacuna(8, "Antineumocóccica"));
			for (Vacuna vacuna : vacunas) {
				vacunaService = new VacunaService(context);
				vacunaService.addVacuna(vacuna);
			}
		} else {
			Log.e("Error", "Ya existe la tabla");
		}
	}

	public void consultarVacunas(Context context) {
		vacunaService = new VacunaService(context);
		vacunas = vacunaService.getAllVacunas();
	}

	public void cargarTablaCalendarioVacunacion(Context context) {
		calendarioVacunacionService = new CalendarioVacunacionService(context);
		if (!calendarioVacunacionService.existeTabla()) {
			calendarioVacunaciones = new ArrayList<CalendarioVacunacion>();
			calendarioVacunaciones.add(new CalendarioVacunacion(1, 0, "Recien Nacido"));
			calendarioVacunaciones.add(new CalendarioVacunacion(2, 0, "Recien Nacido"));
			calendarioVacunaciones.add(new CalendarioVacunacion(2, 2, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(2, 6, "2° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(3, 2, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(3, 4, "2° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(3, 6, "3° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(3, 15, "Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 2, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 4, "2° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 6, "3° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 15, "Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 48, "Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(4, 120, "Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(5, 2, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(5, 4, "2° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(5, 6, "3° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(5, 48, "Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(6, 15, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(6, 84, "2° Refuerzo"));
			calendarioVacunaciones.add(new CalendarioVacunacion(7, 15, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(8, 2, "1° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(8, 4, "2° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(8, 6, "3° Dosis"));
			calendarioVacunaciones.add(new CalendarioVacunacion(8, 15, "Refuerzo"));
			for (CalendarioVacunacion calendarioVacunaciones : calendarioVacunaciones) {
				calendarioVacunacionService = new CalendarioVacunacionService(context);
				calendarioVacunacionService.addCalendarioVacunacion(calendarioVacunaciones);
			}
		} else {
			Log.e("Error", "Ya existe la tabla");
			calendarioVacunaciones = calendarioVacunacionService.getAllCalendarioVacunacion();
		}
	}

	public void cargarTablaUsuarioCalendario(Context context) {
		usuarioCalendarioService = new UsuarioCalendarioService(context);
		usuarioCalendarioService.existeTabla();
	}

	public boolean validarVacuna(int meses) {
		Calendar now = Calendar.getInstance();
		Calendar edadActual = Calendar.getInstance();
		edadActual.setTimeInMillis(usuario.getFechaNacimiento());
		edadActual.add(Calendar.MONTH, meses);
		return edadActual.before(now);
	}

	public boolean aplicarVacunaUsuario(Context context, int vacuna, int calendario, long fechaAplicacion) {
		boolean bandera = true;
		usuarioCalendarioService = new UsuarioCalendarioService(context);
		UsuarioCalendario usuarioCalendario = new UsuarioCalendario(usuario.getIdUsuario(), vacuna, calendario,
				fechaAplicacion);
		bandera = usuarioCalendarioService.addUsuarioCalendario(usuarioCalendario) > 0;

		return bandera;
	}

	public ArrayList<String> obtenerVacunasAplicadasUsuario(Context context) {
		ArrayList<String> datos = new ArrayList<>();

		usuarioCalendarioService = new UsuarioCalendarioService(context);
		ArrayList<UsuarioCalendario> usuarioCalendarioLista = usuarioCalendarioService.getCalendarioVacunacion(usuario);
		if (!usuarioCalendarioLista.isEmpty()) {
			long vacunaInicial = usuarioCalendarioLista.get(0).getIdVacuna();
			vacunaService = new VacunaService(context);
			datos.add(vacunaService.getVacuna(new Vacuna(vacunaInicial, "")).getNombreVacuna());
			for (UsuarioCalendario usrCal : usuarioCalendarioLista) {
				if (usrCal != null) {
					if (usrCal.getIdVacuna() != vacunaInicial) {
						vacunaInicial = usuarioCalendarioLista.get(0).getIdVacuna();
						vacunaService = new VacunaService(context);
						datos.add(vacunaService.getVacuna(new Vacuna(vacunaInicial, "")).getNombreVacuna());
					}
				}
			}
		}

		return datos;
	}
}

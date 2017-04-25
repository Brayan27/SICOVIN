package com.programa.model;

public class Usuario {

	private long idUsuario;
	private String Cedula;
	private String Nombre;
	private int MenorAnio;
	private int Edad;
	private double Peso;
	private double Estatura;
	private String Provincia;
	private String Canton;
	private String Distrito;
	private String Contrasena;

	public Usuario(long idusuario, String cedula, String nombre, int menorAnio, int edad, double peso, double estatura,
			String provincia, String canton, String distrito, String contrasena) {
		super();
		idUsuario = idusuario;
		Cedula = cedula;
		Nombre = nombre;
		MenorAnio = menorAnio;
		Edad = edad;
		Peso = peso;
		Estatura = estatura;
		Provincia = provincia;
		Canton = canton;
		Distrito = distrito;
		Contrasena = contrasena;
	}

	public Usuario() {
		super();
		idUsuario = 0;
		Cedula = "";
		Nombre = "";
		MenorAnio = 0;
		Edad = 0;
		Peso = 0;
		Estatura = 0;
		Provincia = "";
		Canton = "";
		Distrito = "";
		Contrasena = "";
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idusuario) {
		idUsuario = idusuario;
	}

	public String getCedula() {
		return Cedula;
	}

	public void setCedula(String cedula) {
		Cedula = cedula;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getMenorAnio() {
		return MenorAnio;
	}

	public void setMenorAnio(int menorAnio) {
		MenorAnio = menorAnio;
	}

	public double getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		Edad = edad;
	}

	public double getPeso() {
		return Peso;
	}

	public void setPeso(double peso) {
		Peso = peso;
	}

	public double getEstatura() {
		return Estatura;
	}

	public void setEstatura(double estatura) {
		Estatura = estatura;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public String getCanton() {
		return Canton;
	}

	public void setCanton(String canton) {
		Canton = canton;
	}

	public String getDistrito() {
		return Distrito;
	}

	public void setDistrito(String distrito) {
		Distrito = distrito;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		Contrasena = contrasena;
	}

}

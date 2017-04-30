package com.programa.model;

public class Usuario {

	private long idUsuario;
	private String Cedula;
	private String Nombre;
	private long FechaNacimiento;
	private double Peso;
	private double Estatura;
	private String Provincia;
	private String Canton;
	private String Distrito;
	private String Contrasena;

	public Usuario(long idusuario, String cedula, String nombre, long fechaNacimiento, double peso, double estatura,
			String provincia, String canton, String distrito, String contrasena) {
		super();
		idUsuario = idusuario;
		Cedula = cedula;
		Nombre = nombre;
		FechaNacimiento = fechaNacimiento;
		Peso = peso;
		Estatura = estatura;
		Provincia = provincia;
		Canton = canton;
		Distrito = distrito;
		Contrasena = contrasena;
	}

	public Usuario() {
		super();
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

	public long getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(long fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
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

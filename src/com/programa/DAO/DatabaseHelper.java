package com.programa.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sicovin.db";
	private static final int DATABASE_VERSION = 1;

	// TABLE USUARIO
	private static final String TABLE_NAME_USUARIO = "Usuario";
	private static final String TABLE_CREATE_USUARIO = "CREATE TABLE " + TABLE_NAME_USUARIO
			+ " (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, Cedula TEXT, Nombre TEXT, FechaNacimiento INTEGER, "
			+ "Peso REAL, Estatura REAL, Provincia TEXT, Canton TEXT, Distrito TEXT," + "Contrasena TEXT)";

	// TABLE VACUNA
	private static final String TABLE_NAME_VACUNA = "Vacuna";
	private static final String TABLE_CREATE_VACUNA = "CREATE TABLE " + TABLE_NAME_VACUNA
			+ "(idVacuna INTEGER PRIMARY KEY AUTOINCREMENT, nombreVacuna TEXT)";

	// TABLE CalendarioVacunacion
	private static final String TABLE_NAME_CALENDARIO_VACUNA = "CalendarioVacunacion";
	private static final String TABLE_CREATE_CALENDARIO_VACUNA = "CREATE TABLE " + TABLE_NAME_CALENDARIO_VACUNA
			+ " (idVacuna INTEGER, edadAplicacion INTEGER, tipo TEXT, PRIMARY KEY ( idVacuna, edadAplicacion),FOREIGN KEY(idVacuna) REFERENCES Vacuna(idVacuna))";

	// TABLE UsuarioCalendario
	private static final String TABLE_NAME_USUARIO_CALENDARIO_VACUNA = "UsuarioCalendario";
	private static final String TABLE_CREATE_USUARIO_CALENDARIO_VACUNA = "CREATE TABLE "
			+ TABLE_NAME_USUARIO_CALENDARIO_VACUNA
			+ " (idUsuario INTEGER,idVacuna INTEGER, edadAplicacion INTEGER, fechaAplicacion INTEGER,"
			+ "PRIMARY KEY(idUsuario,idVacuna,edadAplicacion),"
			+ "FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),"
			+ "FOREIGN KEY(idVacuna,edadAplicacion) REFERENCES CalendarioVacunacion(idVacuna,edadAplicacion))";

	// TABLE UsuarioCalendario
	private static final String TABLE_NAME_CITA = "Cita";
	private static final String TABLE_CREATE_CITA = "CREATE TABLE " + TABLE_NAME_CITA
			+ " (idUsuario INTEGER,idVacuna INTEGER, edadAplicacion INTEGER, fechaCita INTEGER, se_Aplico INTEGER, "
			+ "PRIMARY KEY(idUsuario,idVacuna,edadAplicacion),"
			+ "FOREIGN KEY(idUsuario, idVacuna, edadAplicacion) REFERENCES UsuarioCalendario(idUsuario,idVacuna,edadAplicacion))";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE_USUARIO);
		db.execSQL(TABLE_CREATE_VACUNA);
		db.execSQL(TABLE_CREATE_CALENDARIO_VACUNA);
		db.execSQL(TABLE_CREATE_USUARIO_CALENDARIO_VACUNA);
		db.execSQL(TABLE_CREATE_CITA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_USUARIO);
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_VACUNA);
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_CALENDARIO_VACUNA);
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_USUARIO_CALENDARIO_VACUNA);
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME_CITA);

		onCreate(db);
	}

}

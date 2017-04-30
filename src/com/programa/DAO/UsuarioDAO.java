package com.programa.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sicovin.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "Usuario";
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, Cedula TEXT, Nombre TEXT, FechaNacimiento INTEGER, "
			+ "Peso REAL, Estatura REAL, Provincia TEXT, Canton TEXT, Distrito TEXT," + "Contrasena TEXT)";

	public UsuarioDAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
		db.execSQL(TABLE_CREATE);
	}

}

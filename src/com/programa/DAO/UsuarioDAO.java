package com.programa.DAO;

import com.programa.model.Usuario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDAO extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sicovin.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "Usuario";
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, Cedula TEXT, Nombre TEXT, MenorAnio INTEGER, "
			+ "Edad INTEGER, Peso REAL, Estatura REAL, Provincia TEXT, Canton TEXT, Distrito TEXT,"
			+ "Contrasena TEXT)";

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

	// public void registrarUsuario(Usuario usuario) {
	// SQLiteDatabase db = this.getWritableDatabase();
	// db.execSQL(
	// "INSERT INTO Usuario (Cedula, Nombre, MenorAnio, Edad, Peso, Estatura,
	// Provincia, Canton, Distrito, Contrasena) VALUES('"
	// + usuario.getCedula() + "','" + usuario.getNombre() + "'," +
	// usuario.getMenorAnio() + ","
	// + usuario.getEdad() + "," + usuario.getPeso() + "," +
	// usuario.getEstatura() + ",'"
	// + usuario.getProvincia() + "','" + usuario.getCanton() + "','" +
	// usuario.getDistrito() + "','"
	// + usuario.getContrasena() + "')");
	// db.close();
	// }

//	public Usuario obtenerUsuario(Usuario usuario) {
//		Usuario usuario = null;
//		SQLiteDatabase db = this.getReadableDatabase();
//		String[] args = new String[] { usuario.getCedula(), usuario.getContrasena() };
//		Cursor c = db.rawQuery(" SELECT * FROM Usuario WHERE Cedula='402110772' AND  Contrasena='gaby'", null);
//		db.close();
//		if (c.moveToFirst()) {
//			// Recorremos el cursor hasta que no haya más registros
//			do {
//				usuario = new Usuario(c.getString(0), c.getString(1), c.getInt(2), c.getInt(3), c.getDouble(4),
//						c.getDouble(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9));
//				break;
//			} while (c.moveToNext());
//		}
//		return usuario;
//	}

}

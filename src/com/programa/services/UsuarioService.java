package com.programa.services;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioService {

	public static final String LOGTAG = "USR_MNGMNT_SYS";

	SQLiteOpenHelper dbhandler;
	SQLiteDatabase database;

	public UsuarioService(Context context) {
		dbhandler = new DatabaseHelper(context);
	}

	public void open() {
		Log.i(LOGTAG, "Database Opened");
		database = dbhandler.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Database Closed");
		dbhandler.close();

	}

	public Usuario addUsuario(Usuario usuario) {
		open();
		ContentValues values = new ContentValues();
		values.put("Cedula", usuario.getCedula());
		values.put("Nombre", usuario.getNombre());
		values.put("FechaNacimiento", usuario.getFechaNacimiento());
		values.put("Peso", usuario.getPeso());
		values.put("Estatura", usuario.getEstatura());
		values.put("Provincia", usuario.getProvincia());
		values.put("Canton", usuario.getCanton());
		values.put("Distrito", usuario.getDistrito());
		values.put("Contrasena", usuario.getContrasena());
		long insertid = database.insert("Usuario", null, values);
		usuario.setIdUsuario(insertid);
		close();
		return usuario;
	}

	public Usuario getUsuario(Usuario usr) {
		open();
		Cursor cursor = database.query("Usuario", null, "Cedula=? AND Contrasena=?",
				new String[] { usr.getCedula(), usr.getContrasena() }, null, null, null, null);

		Usuario usuario = null;

		if (cursor != null) {
			cursor.moveToFirst();
			usuario = new Usuario(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
					Long.parseLong(cursor.getString(3)), Double.parseDouble(cursor.getString(4)),
					Double.parseDouble(cursor.getString(5)), cursor.getString(6), cursor.getString(7),
					cursor.getString(8), cursor.getString(9));
		}
		close();
		return usuario;
	}

	// Updating User
	public int updateUsuario(Usuario usuario) {
		open();
		ContentValues values = new ContentValues();
		values.put("Cedula", usuario.getCedula());
		values.put("Nombre", usuario.getNombre());
		values.put("FechaNacimiento", usuario.getFechaNacimiento());
		values.put("Peso", usuario.getPeso());
		values.put("Estatura", usuario.getEstatura());
		values.put("Provincia", usuario.getProvincia());
		values.put("Canton", usuario.getCanton());
		values.put("Distrito", usuario.getDistrito());
		if (!usuario.getContrasena().equals("")) {
			values.put("Contrasena", usuario.getContrasena());
		}

		// updating row
		int affectedRow = database.update("Usuario", values, "idUsuario=?",
				new String[] { String.valueOf(usuario.getIdUsuario()) });
		close();
		return affectedRow;
	}

	public boolean existeUsuario(Usuario usr) {
		open();
		Cursor cursor = database.query("Usuario", null, "Cedula=?", new String[] { usr.getCedula() }, null, null, null,
				null);
		boolean bandera = cursor.moveToFirst();
		close();
		return bandera;
	}

}

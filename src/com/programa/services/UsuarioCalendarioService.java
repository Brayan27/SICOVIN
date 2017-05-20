package com.programa.services;

import java.util.ArrayList;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.Usuario;
import com.programa.model.UsuarioCalendario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuarioCalendarioService {

	public static final String LOGTAG = "USR_MNGMNT_SYS";

	SQLiteOpenHelper dbhandler;
	SQLiteDatabase database;

	public UsuarioCalendarioService(Context context) {
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

	public boolean existeTabla() {
		boolean existe = true;
		open();
		Cursor cursor = database.query("sqlite_master", null, "type=? AND name=?",
				new String[] { "table", "UsuarioCalendario" }, null, null, null, null);
		existe = cursor.moveToFirst();
		if (!existe) {
			database.execSQL(
					"CREATE TABLE UsuarioCalendario (idUsuario INTEGER,idVacuna INTEGER, edadAplicacion INTEGER, fechaAplicacion INTEGER,"
							+ "PRIMARY KEY(idUsuario,idVacuna,edadAplicacion),"
							+ "FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),"
							+ "FOREIGN KEY(idVacuna,edadAplicacion) REFERENCES CalendarioVacunacion(idVacuna,edadAplicacion))");
		}
		close();
		return existe;
	}

	public long addUsuarioCalendario(UsuarioCalendario usuarioCalendario) {
		open();
		ContentValues values = new ContentValues();
		values.put("idUsuario", usuarioCalendario.getIdUsuario());
		values.put("idVacuna", usuarioCalendario.getIdVacuna());
		values.put("edadAplicacion", usuarioCalendario.getEdadAplicacion());
		values.put("fechaAplicacion", usuarioCalendario.getFechaAplicacion());
		long resultado = database.insert("UsuarioCalendario", null, values);
		close();
		return resultado;
	}

	public ArrayList<UsuarioCalendario> getCalendarioVacunacion(Usuario usr) {
		open();
		Cursor cursor = database.query("UsuarioCalendario", null, "idUsuario=?",
				new String[] { String.valueOf(usr.getIdUsuario()) }, null, null, null, null);

		ArrayList<UsuarioCalendario> calendarioVacunacion = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					calendarioVacunacion.add(new UsuarioCalendario(Long.parseLong(cursor.getString(0)),
							Long.parseLong(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
							Long.parseLong(cursor.getString(3))));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}
		return calendarioVacunacion;
	}

}

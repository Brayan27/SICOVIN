package com.programa.services;

import java.util.ArrayList;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.Cita;
import com.programa.model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CitaService {

	public static final String LOGTAG = "USR_MNGMNT_SYS";

	SQLiteOpenHelper dbhandler;
	SQLiteDatabase database;

	public CitaService(Context context) {
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
		Cursor cursor = database.query("sqlite_master", null, "type=? AND name=?", new String[] { "table", "Cita" },
				null, null, null, null);
		existe = cursor.moveToFirst();
		if (!existe) {
			database.execSQL(
					"CREATE TABLE Cita (idUsuario INTEGER,idVacuna INTEGER, edadAplicacion INTEGER, fechaCita INTEGER, se_Aplico INTEGER, "
							+ "PRIMARY KEY(idUsuario,idVacuna,edadAplicacion),"
							+ "FOREIGN KEY(idUsuario, idVacuna, edadAplicacion) REFERENCES UsuarioCalendario(idUsuario,idVacuna,edadAplicacion))");
		}
		close();
		return existe;
	}

	public long addCita(Cita cita) {
		open();
		ContentValues values = new ContentValues();
		values.put("idUsuario", cita.getIdUsuario());
		values.put("idVacuna", cita.getIdVacuna());
		values.put("edadAplicacion", cita.getEdadAplicacion());
		values.put("fechaCita", cita.getFechaCita());
		values.put("se_Aplico", cita.getSe_Aplico());
		long resultado = database.insert("Cita", null, values);
		close();
		return resultado;
	}

	public ArrayList<Cita> getCitasPendientesUsuario(Usuario usr) {
		open();
		Cursor cursor = database.query("Cita", null, "idUsuario=? and se_Aplico=?",
				new String[] { String.valueOf(usr.getIdUsuario()), "0" }, null, null, null, null);

		ArrayList<Cita> citasPendientes = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					citasPendientes.add(new Cita(
							Long.parseLong(cursor.getString(0)),
							Long.parseLong(cursor.getString(1)), 
							Integer.parseInt(cursor.getString(2)),
							Long.parseLong(cursor.getString(3)), 
							Integer.parseInt(cursor.getString(4))));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}
		return citasPendientes;
	}
	
	public ArrayList<Cita> getCitasAplicadasUsuario(Usuario usr) {
		open();
		Cursor cursor = database.query("Cita", null, "idUsuario=? and se_Aplico=?",
				new String[] { String.valueOf(usr.getIdUsuario()), "1" }, null, null, null, null);

		ArrayList<Cita> citasPendientes = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					citasPendientes.add(new Cita(
							Long.parseLong(cursor.getString(0)),
							Long.parseLong(cursor.getString(1)), 
							Integer.parseInt(cursor.getString(2)),
							Long.parseLong(cursor.getString(3)), 
							Integer.parseInt(cursor.getString(4))));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}
		return citasPendientes;
	}

}

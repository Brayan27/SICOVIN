package com.programa.services;

import java.util.ArrayList;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.Usuario;
import com.programa.model.Vacuna;
import com.programa.model.VacunasCont;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class VacunaService {

	public static final String LOGTAG = "USR_MNGMNT_SYS";

	SQLiteOpenHelper dbhandler;
	SQLiteDatabase database;

	public VacunaService(Context context) {
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
		// SELECT name FROM sqlite_master WHERE type='table' AND
		// name='table_name';
		Cursor cursor = database.query("sqlite_master", null, "type=? AND name=?", new String[] { "table", "Vacuna" },
				null, null, null, null);
		existe = cursor.moveToFirst();
		if (!existe) {
			database.execSQL("CREATE TABLE Vacuna (idVacuna INTEGER PRIMARY KEY AUTOINCREMENT, nombreVacuna TEXT)");
		}
		close();
		return existe;
	}

	public Vacuna addVacuna(Vacuna vacuna) {
		open();
		ContentValues values = new ContentValues();
		values.put("idVacuna", vacuna.getIdVacuna());
		values.put("nombreVacuna", vacuna.getNombreVacuna());
		long insertid = database.insert("Vacuna", null, values);
		vacuna.setIdVacuna(insertid);
		close();
		return vacuna;
	}

	public Vacuna getVacuna(Vacuna vac) {
		open();
		Vacuna vacuna = null;

		Cursor cursor = database.query("Vacuna", null, "idVacuna=?", new String[] { String.valueOf(vac.getIdVacuna()) },
				null, null, null, null);

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				vacuna = new Vacuna(Long.parseLong(cursor.getString(0)), cursor.getString(1));
			}
			cursor.close();
		}
		close();
		return vacuna;
	}

	// Updating Vacuna
	public int updateVacuna(Vacuna vacuna) {
		open();
		ContentValues values = new ContentValues();
		values.put("nombreVacuna", vacuna.getNombreVacuna());

		// updating row
		int affectedRow = database.update("Vacuna", values, "idVacuna=?",
				new String[] { String.valueOf(vacuna.getIdVacuna()) });
		close();
		return affectedRow;
	}

	public ArrayList<Vacuna> getAllVacunas() {

		open();

		Cursor cursor = null;

		try {
			cursor = database.rawQuery("select * from Vacuna", null);
		} catch (Exception ex) {
			Log.e("Error", ex.getMessage());
		}

		ArrayList<Vacuna> vacunas = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					vacunas.add(new Vacuna(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}

		close();

		return vacunas;
	}

	public ArrayList<VacunasCont> vacunasAplicadasUsuario(Usuario usuario) {
		ArrayList<VacunasCont> vacunas = new ArrayList<>();
		Cursor cursor = null;

		open();
		cursor = database.rawQuery(
				"select Vacuna.nombreVacuna, UsuarioCalendario.edadAplicacion, "
						+ "UsuarioCalendario.fechaAplicacion from Vacuna inner join UsuarioCalendario ON "
						+ "Vacuna.idVacuna = UsuarioCalendario.idVacuna and UsuarioCalendario.idUsuario=?",
				new String[] { String.valueOf(usuario.getIdUsuario()) });

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					vacunas.add(new VacunasCont(cursor.getString(0), Integer.parseInt(cursor.getString(1)),
							Long.valueOf(cursor.getString(2))));
					cursor.moveToNext();
				}
			}
			cursor.close();
		}

		close();

		return vacunas;
	}

	public ArrayList<VacunasCont> vacunasPendientesUsuario(Usuario usuario) {
		ArrayList<VacunasCont> vacunas = new ArrayList<>();
		Cursor cursor = null;

		open();
		cursor = database.rawQuery(
				"select distinct Vacuna.nombreVacuna,CalendarioVacunacion.edadAplicacion "
						+ "from CalendarioVacunacion left join UsuarioCalendario "
						+ "ON CalendarioVacunacion.idVacuna!=UsuarioCalendario.idVacuna and "
						+ "CalendarioVacunacion.edadAplicacion!=UsuarioCalendario.edadAplicacion "
						+ "left join Vacuna on Vacuna.idVacuna=CalendarioVacunacion.idVacuna where "
						+ "UsuarioCalendario.idUsuario=? except "
						+ "select Vacuna.nombreVacuna, UsuarioCalendario.edadAplicacion"
						+ " from Vacuna inner join UsuarioCalendario ON "
						+ "Vacuna.idVacuna = UsuarioCalendario.idVacuna and UsuarioCalendario.idUsuario=?",
				new String[] { String.valueOf(usuario.getIdUsuario()), String.valueOf(usuario.getIdUsuario()) });
		// https://www.techonthenet.com/sqlite/except.php

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					vacunas.add(new VacunasCont(cursor.getString(0), Integer.parseInt(cursor.getString(1)), 0));
					cursor.moveToNext();
				}
			}
			cursor.close();
		}

		close();

		return vacunas;
	}

}

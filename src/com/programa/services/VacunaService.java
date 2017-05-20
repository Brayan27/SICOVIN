package com.programa.services;

import java.util.ArrayList;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.Vacuna;

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
		return vacunas;
	}

}

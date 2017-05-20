package com.programa.services;

import java.util.ArrayList;

import com.programa.DAO.DatabaseHelper;
import com.programa.model.CalendarioVacunacion;
import com.programa.model.Vacuna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CalendarioVacunacionService {

	public static final String LOGTAG = "USR_MNGMNT_SYS";

	SQLiteOpenHelper dbhandler;
	SQLiteDatabase database;

	public CalendarioVacunacionService(Context context) {
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
				new String[] { "table", "CalendarioVacunacion" }, null, null, null, null);
		existe = cursor.moveToFirst();
		if (!existe) {
			database.execSQL(
					"CREATE TABLE CalendarioVacunacion (idVacuna INTEGER, edadAplicacion INTEGER, tipo TEXT,PRIMARY KEY ( idVacuna, edadAplicacion),FOREIGN KEY(idVacuna) REFERENCES Vacuna(idVacuna))");
		}
		close();
		return existe;
	}

	public CalendarioVacunacion addCalendarioVacunacion(CalendarioVacunacion calendarioVacunacion) {
		open();
		ContentValues values = new ContentValues();
		values.put("idVacuna", calendarioVacunacion.getIdVacuna());
		values.put("edadAplicacion", calendarioVacunacion.getEdadAplicacion());
		values.put("tipo", calendarioVacunacion.getTipo());
		database.insert("CalendarioVacunacion", null, values);
		close();
		return calendarioVacunacion;
	}

	public ArrayList<CalendarioVacunacion> getCalendarioVacunacionVacuna(Vacuna vac) {
		open();
		Cursor cursor = database.query("CalendarioVacunacion", null, "idVacuna=?",
				new String[] { String.valueOf(vac.getIdVacuna()) }, null, null, null, null);

		ArrayList<CalendarioVacunacion> calnedarioVacunas = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					calnedarioVacunas.add(new CalendarioVacunacion(Integer.parseInt(cursor.getString(0)),
							Integer.parseInt(cursor.getString(1)), cursor.getString(2)));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}
		return calnedarioVacunas;
	}

	// Updating CalendarioVacunacion
	public int updateCalendarioVacunacion(CalendarioVacunacion calendarioVacunacion) {
		open();
		ContentValues values = new ContentValues();
		values.put("idVacuna", calendarioVacunacion.getIdVacuna());
		values.put("edadAplicacion", calendarioVacunacion.getEdadAplicacion());
		values.put("tipo", calendarioVacunacion.getTipo());

		// updating row
		int affectedRow = database.update("CalendarioVacunacion", values, "idVacuna=? AND edadAplicacion=?",
				new String[] { String.valueOf(calendarioVacunacion.getIdVacuna()),
						String.valueOf(calendarioVacunacion.getEdadAplicacion()) });
		close();
		return affectedRow;
	}

	public ArrayList<CalendarioVacunacion> getAllCalendarioVacunacion() {

		open();

		Cursor cursor = null;
		cursor = database.rawQuery("select * from CalendarioVacunacion", null);

		ArrayList<CalendarioVacunacion> calendarioVacunas = new ArrayList<>();

		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (cursor.isAfterLast() == false) {
					calendarioVacunas.add(new CalendarioVacunacion(Integer.parseInt(cursor.getString(0)),
							Integer.parseInt(cursor.getString(1)), cursor.getString(2)));
					cursor.moveToNext();
				}

			}
			cursor.close();
		}
		return calendarioVacunas;
	}

}

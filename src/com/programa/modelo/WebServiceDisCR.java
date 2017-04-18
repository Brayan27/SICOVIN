package com.programa.modelo;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class WebServiceDisCR extends AsyncTask<String, Integer, String> {

	private int metodo;
	private ArrayList<Provincia> provincias;

	@Override
	protected String doInBackground(String... arg0) {
		String cadena = arg0[0];
		metodo = Integer.parseInt(arg0[1]);

		String devuelve = "";
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet del = new HttpGet(cadena);

		del.setHeader("content-type", "application/json");

		provincias = new ArrayList<>();

		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONObject object = new JSONObject(respStr);
			JSONArray respJSON = object.names();

			for (int i = 0; i < respJSON.length(); i++) {
				JSONObject obj = object.getJSONObject(respJSON.getString(i));
				if (metodo == 1) {
					int idProvincia = Integer.parseInt(respJSON.getString(i));
					String nombreProvincia = obj.getString("Nombre");
					provincias.add(new Provincia(idProvincia, nombreProvincia));
				}
			}

			devuelve += respStr;
		} catch (Exception ex) {
			Log.e("ServicioRest", "Error!", ex);
		}

		return devuelve;

	}

	@Override
	protected void onCancelled(String aVoid) {
		super.onCancelled(aVoid);
	}

	@Override
	protected void onPostExecute(String aVoid) {
		Globales.obtenerInstancia().setDatos(aVoid);
		if (metodo == 1) {
			Globales.obtenerInstancia().setProvincias(provincias);
		}
	}

	@Override
	protected void onPreExecute() {
		Globales.obtenerInstancia().setDatos("");
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

}

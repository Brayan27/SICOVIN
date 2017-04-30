package com.programa.services;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.programa.controller.Controller;
import com.programa.model.Canton;
import com.programa.model.Distrito;
import com.programa.model.Provincia;

import android.os.AsyncTask;
import android.util.Log;

public class WebServiceDisCR extends AsyncTask<String, Integer, String> {

	private int metodo;
	private ArrayList<Provincia> provincias;
	private ArrayList<Canton> cantones;
	private ArrayList<Distrito> distritos;

	@Override
	protected String doInBackground(String... arg0) {
		String cadena = arg0[0];
		metodo = Integer.parseInt(arg0[1]);

		String devuelve = "";
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet del = new HttpGet(cadena);

		del.setHeader("content-type", "application/json");

		provincias = new ArrayList<>();
		cantones = new ArrayList<>();
		distritos = new ArrayList<>();

		try {
			HttpResponse resp = httpClient.execute(del);
			String respStr = EntityUtils.toString(resp.getEntity());
			JSONObject object = new JSONObject(respStr);
			JSONArray respJSON = object.names();
			System.out.println(respStr);

			for (int i = 0; i < respJSON.length(); i++) {
				JSONObject obj = object.getJSONObject(respJSON.getString(i));
				if (metodo == 1) {
					int idProvincia = Integer.parseInt(respJSON.getString(i));
					String nombreProvincia = obj.getString("Nombre");
					provincias.add(new Provincia(idProvincia, nombreProvincia));
				}
				if (metodo == 2) {
					if (obj.getString("Provincia").equals(arg0[2])) {
						int idCanton = Integer.parseInt(respJSON.getString(i));
						int idProvincia = Integer.valueOf(obj.getString("Provincia"));
						String nombreCanton = obj.getString("Nombre");
						cantones.add(new Canton(idProvincia, idCanton, nombreCanton));
					}
				}
				if (metodo == 3) {
					if (obj.getString("Cantón").equals(arg0[2])) {
						int idDistrito = Integer.parseInt(respJSON.getString(i));
						int idCanton = Integer.valueOf(obj.getString("Cantón"));
						String nombreDistrito = obj.getString("Nombre");
						distritos.add(new Distrito(idDistrito, idCanton, nombreDistrito));
					}
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
		if (metodo == 1) {
			Controller.obtenerInstancia().setProvincias(provincias);
		}
		if (metodo == 2) {
			Controller.obtenerInstancia().setCantones(cantones);
		}
		if (metodo == 3) {
			Controller.obtenerInstancia().setDistritos(distritos);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

}

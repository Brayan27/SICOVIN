package com.programa.sicovin;

import java.util.ArrayList;

import com.programa.controller.Controller;
import com.programa.model.VacunasCont;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class VacunasActivity extends Activity {

	private Button btnAplicadas;
	private Button btnPendientes;
	private ListView listaAplicadas;
	private ListView listaPendientes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vacunas);

		inicializarComponentes();
		ajustarEventos();
		cargarDatos();

	}

	private void inicializarComponentes() {
		btnAplicadas = (Button) findViewById(R.id.btnAplicadas);
		btnPendientes = (Button) findViewById(R.id.btnPendientes);
		listaAplicadas = (ListView) findViewById(R.id.listaAplicadas);
		listaPendientes = (ListView) findViewById(R.id.listaPendientes);
	}

	private void cargarDatos() {

		cargarVacunasAplicadas();
		cargarVacunasPendientes();
	}

	private void ajustarEventos() {
		btnAplicadas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				listaAplicadas.setVisibility(View.VISIBLE);
				listaPendientes.setVisibility(View.GONE);
			}
		});
		btnPendientes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				listaPendientes.setVisibility(View.VISIBLE);
				listaAplicadas.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void cargarVacunasAplicadas() {

		ArrayList<VacunasCont> vacunas = Controller.obtenerInstancia().obtenerVacunasAplicadasUsuario(this);
		String[] datos = new String[vacunas.size()];

		for (int i = 0; i < vacunas.size(); i++) {
			datos[i] = vacunas.get(i).toString();
		}

		listaAplicadas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos));
	}

	private void cargarVacunasPendientes() {
		ArrayList<VacunasCont> vacunas = Controller.obtenerInstancia().obtenerVacunasPendientesUsuario(this);
		String[] datos = new String[vacunas.size()];

		for (int i = 0; i < vacunas.size(); i++) {
			datos[i] = vacunas.get(i).imprimirPendiente();
		}

		listaPendientes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos));
	}
}

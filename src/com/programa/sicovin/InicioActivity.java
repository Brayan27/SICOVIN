package com.programa.sicovin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends Activity {

	// https://www.youtube.com/watch?v=fH3S6HbbZ1k

	private Button botonIrInicio;
	private Button botonVacunas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		inicializarComponentes();
		ajustarEventos();

	}

	private void inicializarComponentes() {
		botonIrInicio = (Button) findViewById(R.id.button1);
		botonVacunas = (Button) findViewById(R.id.button2);
	}

	private void ajustarEventos() {
		botonIrInicio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), ModificarActivity.class);
				startActivity(i);
			}
		});
		botonVacunas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), VacunasActivity.class);
				startActivity(i);
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
}

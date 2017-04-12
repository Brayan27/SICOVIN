package com.programa.sicovin;

import java.util.ArrayList;

import com.programa.modelo.Globales;
import com.programa.modelo.Provincia;
import com.programa.modelo.WebServiceDisCR;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	// http://data.okfn.org/data/investigacion/divisiones-territoriales-costa-rica

	private CheckBox menorAnio;
	private TextView textEdad;
	private EditText reg_edad;
	private EditText reg_edadMeses;
	private Spinner spinnerProvincias;
	private WebServiceDisCR servicio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		inicializarComponentes();
		ajustarEventos();
		serviceSpinnerProvincias();
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

	private void inicializarComponentes() {
		menorAnio = (CheckBox) findViewById(R.id.checkBoxMenor);
		textEdad = (TextView) findViewById(R.id.textEdad);
		textEdad.setText("Edad en Años");
		reg_edad = (EditText) findViewById(R.id.reg_edad);
		reg_edadMeses = (EditText) findViewById(R.id.reg_edadMeses);
		spinnerProvincias = (Spinner) findViewById(R.id.spinnerProvincia);
	}

	private void ajustarEventos() {
		menorAnio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menorAnio.isChecked()) {
					textEdad.setText("Edad en Meses");
					reg_edad.setVisibility(View.GONE);
					reg_edadMeses.setVisibility(View.VISIBLE);
				} else {
					textEdad.setText("Edad en Años");
					reg_edadMeses.setVisibility(View.GONE);
					reg_edad.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	private void serviceSpinnerProvincias() {
		if (Globales.obtenerInstancia().getProvincias().isEmpty()) {
			Globales.obtenerInstancia().setRegisterActivity(this);
			servicio = new WebServiceDisCR();
			servicio.execute(
					"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm1-provincias.json",
					"1");
		}else{
			cargarSpinnerProvincias();
		}
	}

	public void cargarSpinnerProvincias() {

		ArrayList<Provincia> provincias = Globales.obtenerInstancia().getProvincias();

		String[] datos = new String[provincias.size()];

		for (int i = 0; i < provincias.size(); i++) {
			datos[i] = provincias.get(i).getNombreProvincia();
		}

		cargarSpinner(spinnerProvincias, datos);

	}

	private void cargarSpinner(Spinner spinner, final String[] datos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), datos[position], Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinner.setAdapter(adapter);
	}

}

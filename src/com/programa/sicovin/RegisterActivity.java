package com.programa.sicovin;

import java.util.ArrayList;

import com.programa.controller.Controller;
import com.programa.model.Canton;
import com.programa.model.Distrito;
import com.programa.model.Provincia;
import com.programa.model.Usuario;
import com.programa.services.WebServiceDisCR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	// http://data.okfn.org/data/investigacion/divisiones-territoriales-costa-rica

	private EditText cedula;
	private EditText nombre;
	private EditText peso;
	private EditText estatura;
	private CheckBox menorAnio;
	private TextView textEdad;
	private EditText reg_edad;
	private EditText reg_edadMeses;
	private Spinner spinnerProvincias;
	private Spinner spinnerCantones;
	private Spinner spinnerDistritos;
	private EditText contrasena;
	private Button botonRegistrar;
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
		cedula = (EditText) findViewById(R.id.reg_cedula);
		nombre = (EditText) findViewById(R.id.reg_nombreCompleto);
		peso = (EditText) findViewById(R.id.reg_peso);
		estatura = (EditText) findViewById(R.id.reg_estatura);
		menorAnio = (CheckBox) findViewById(R.id.checkBoxMenor);
		textEdad = (TextView) findViewById(R.id.textEdad);
		textEdad.setText("Edad en Años");
		reg_edad = (EditText) findViewById(R.id.reg_edad);
		reg_edadMeses = (EditText) findViewById(R.id.reg_edadMeses);
		spinnerProvincias = (Spinner) findViewById(R.id.spinnerProvincia);
		spinnerCantones = (Spinner) findViewById(R.id.spinnerCanton);
		spinnerDistritos = (Spinner) findViewById(R.id.spinnerDistrito);
		contrasena = (EditText) findViewById(R.id.reg_password);
		botonRegistrar = (Button) findViewById(R.id.btnRegister);
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

		botonRegistrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				registrarUsuario();
			}
		});
	}

	private void registrarUsuario() {
		int edad = 0;
		int menor = 0;
		if (menorAnio.isChecked()) {
			menor = 1;
			edad = Integer.valueOf(reg_edadMeses.getText().toString());
		} else {
			edad = Integer.valueOf(reg_edad.getText().toString());
		}
		Usuario usuario = new Usuario();
		usuario.setCedula(cedula.getText().toString());
		usuario.setNombre(nombre.getText().toString());
		usuario.setMenorAnio(menor);
		usuario.setEdad(edad);
		usuario.setPeso(Double.valueOf(peso.getText().toString()));
		usuario.setEstatura(Double.valueOf(estatura.getText().toString()));
		usuario.setProvincia(spinnerProvincias.getSelectedItem().toString());
		usuario.setCanton(spinnerCantones.getSelectedItem().toString());
		usuario.setDistrito(spinnerDistritos.getSelectedItem().toString());
		usuario.setContrasena(contrasena.getText().toString());
		Controller.obtenerInstancia().registrarUsuario(usuario, this);

		if (Controller.obtenerInstancia().getUsuario() != null) {
			Intent i = new Intent(getApplicationContext(), InicioActivity.class);
			startActivity(i);
		} else {
			Toast.makeText(this, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
		}
	}

	private void serviceSpinnerProvincias() {
		if (Controller.obtenerInstancia().getProvincias().isEmpty()) {
			Controller.obtenerInstancia().setRegisterActivity(this);
			servicio = new WebServiceDisCR();
			servicio.execute(
					"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm1-provincias.json",
					"1");
		} else {
			cargarSpinnerProvincias();
		}
	}

	public void cargarSpinnerProvincias() {

		ArrayList<Provincia> provincias = Controller.obtenerInstancia().getProvincias();

		String[] datos = new String[provincias.size()];

		for (int i = 0; i < provincias.size(); i++) {
			datos[i] = provincias.get(i).getNombreProvincia();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinnerProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				servicio = new WebServiceDisCR();
				servicio.execute(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm2-cantones.json",
						"2", String.valueOf(position + 1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerProvincias.setAdapter(adapter);

	}

	public void cargarSpinnerCantones() {

		ArrayList<Canton> cantones = Controller.obtenerInstancia().getCantones();

		final String[] datos = new String[cantones.size()];

		for (int i = 0; i < cantones.size(); i++) {
			datos[i] = cantones.get(i).getNombreCanton();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinnerCantones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				servicio = new WebServiceDisCR();
				servicio.execute(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm3-distritos.json",
						"3", Controller.obtenerInstancia().getIdCanton(datos[position]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerCantones.setAdapter(adapter);
	}

	public void cargarSpinnerDistritos() {
		ArrayList<Distrito> distritos = Controller.obtenerInstancia().getDistritos();

		final String[] datos = new String[distritos.size()];

		for (int i = 0; i < distritos.size(); i++) {
			datos[i] = distritos.get(i).getNombreDistrito();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinnerDistritos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerDistritos.setAdapter(adapter);
	}

}

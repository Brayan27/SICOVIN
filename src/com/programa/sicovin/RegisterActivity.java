package com.programa.sicovin;

import java.util.ArrayList;
import java.util.Calendar;

import com.programa.controller.Controller;
import com.programa.model.Canton;
import com.programa.model.ClaseUseSpinner;
import com.programa.model.Distrito;
import com.programa.model.Provincia;
import com.programa.model.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends ClaseUseSpinner {

	// http://data.okfn.org/data/investigacion/divisiones-territoriales-costa-rica

	private EditText cedula;
	private EditText nombre;
	private EditText peso;
	private EditText estatura;
	private DatePicker datepicker;
	private Spinner spinnerProvincias;
	private Spinner spinnerCantones;
	private Spinner spinnerDistritos;
	private EditText contrasena;
	private Button botonRegistrar;
	private TextView textInicio;

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
		datepicker = (DatePicker) findViewById(R.id.datePicker1);
		datepicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
		spinnerProvincias = (Spinner) findViewById(R.id.spinnerProvincia);
		spinnerCantones = (Spinner) findViewById(R.id.spinnerCanton);
		spinnerDistritos = (Spinner) findViewById(R.id.spinnerDistrito);
		contrasena = (EditText) findViewById(R.id.reg_password);
		botonRegistrar = (Button) findViewById(R.id.btnRegister);
		textInicio = (TextView) findViewById(R.id.link_to_login);
	}

	private void ajustarEventos() {
		botonRegistrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				registrarUsuario();
			}
		});
		textInicio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
	}

	private void registrarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setCedula(cedula.getText().toString());
		usuario.setNombre(nombre.getText().toString());
		int day = datepicker.getDayOfMonth();
		int month = datepicker.getMonth();
		int year = datepicker.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		usuario.setFechaNacimiento(calendar.getTimeInMillis());
		usuario.setPeso(Double.valueOf(peso.getText().toString()));
		usuario.setEstatura(Double.valueOf(estatura.getText().toString()));
		usuario.setProvincia(spinnerProvincias.getSelectedItem().toString());
		usuario.setCanton(spinnerCantones.getSelectedItem().toString());
		usuario.setDistrito(spinnerDistritos.getSelectedItem().toString());
		usuario.setContrasena(contrasena.getText().toString());

		if (!Controller.obtenerInstancia().existeUsuario(usuario, this)) {
			Controller.obtenerInstancia().registrarUsuario(usuario, this);
			if (Controller.obtenerInstancia().getUsuario() != null) {
				Intent i = new Intent(getApplicationContext(), InicioActivity.class);
				startActivity(i);
			} else {
				Toast.makeText(this, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "No se pudo crear el usuario debido a que ya existe un usuario con este cédula.",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void serviceSpinnerProvincias() {
		if (Controller.obtenerInstancia().getProvincias().isEmpty()) {
			ejecutarWebService(
					"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm1-provincias.json",
					"1", "");
		} else {
			cargarProvincias();
		}
	}

	@Override
	public void cargarProvincias() {

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
				ejecutarWebService(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm2-cantones.json",
						"2", String.valueOf(position + 1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerProvincias.setAdapter(adapter);

	}

	@Override
	public void cargarCantones() {

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
				ejecutarWebService(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm3-distritos.json",
						"3", Controller.obtenerInstancia().getIdCanton(datos[position]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerCantones.setAdapter(adapter);
	}

	@Override
	public void cargarDistritos() {
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

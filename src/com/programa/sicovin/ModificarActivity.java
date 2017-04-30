package com.programa.sicovin;

import java.util.ArrayList;
import java.util.Calendar;

import com.programa.controller.Controller;
import com.programa.model.Usuario;
import com.programa.model.Canton;
import com.programa.model.ClaseUseSpinner;
import com.programa.model.Distrito;
import com.programa.model.Provincia;

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
import android.widget.Toast;

public class ModificarActivity extends ClaseUseSpinner {

	// http://data.okfn.org/data/investigacion/divisiones-territoriales-costa-rica

	private EditText cedulaMod;
	private EditText nombreMod;
	private EditText pesoMod;
	private EditText estaturaMod;
	private DatePicker datepicker2;
	private EditText contrasenaMod;
	private Button botonModificar;
	private int inicio;
	private int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar);

		inicializarComponentes();
		cargarDatos();
		ajustarEventos();
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
		cedulaMod = (EditText) findViewById(R.id.mod_cedula);
		nombreMod = (EditText) findViewById(R.id.mod_nombreCompleto);
		pesoMod = (EditText) findViewById(R.id.mod_peso);
		estaturaMod = (EditText) findViewById(R.id.mod_estatura);
		datepicker2 = (DatePicker) findViewById(R.id.datePicker2);
		spinnerProvincias = (Spinner) findViewById(R.id.spinnerProvinciaMod);
		spinnerCantones = (Spinner) findViewById(R.id.spinnerCantonMod);
		spinnerDistritos = (Spinner) findViewById(R.id.spinnerDistritoMod);
		contrasenaMod = (EditText) findViewById(R.id.mod_password);
		botonModificar = (Button) findViewById(R.id.btnModificar);
	}

	private void cargarDatos() {
		cedulaMod.setText(Controller.obtenerInstancia().getUsuario().getCedula());
		nombreMod.setText(Controller.obtenerInstancia().getUsuario().getNombre());
		pesoMod.setText(String.valueOf(Controller.obtenerInstancia().getUsuario().getPeso()));
		estaturaMod.setText(String.valueOf(Controller.obtenerInstancia().getUsuario().getEstatura()));
		Calendar fecha = Calendar.getInstance();
		fecha.setTimeInMillis(Controller.obtenerInstancia().getUsuario().getFechaNacimiento());
		datepicker2.setMaxDate(Calendar.getInstance().getTimeInMillis());
		datepicker2.updateDate(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));
		inicio = 1;
		pos = 0;
		if (Controller.obtenerInstancia().getProvincias().isEmpty()) {
			ejecutarWebService(
					"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm1-provincias.json",
					"1", "");
		} else {
			cargarProvincias();
		}
	}

	private void ajustarEventos() {
		botonModificar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				modificarUsuario();
			}
		});
	}

	private void modificarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setCedula(cedulaMod.getText().toString());
		usuario.setNombre(nombreMod.getText().toString());
		int day = datepicker2.getDayOfMonth();
		int month = datepicker2.getMonth();
		int year = datepicker2.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		usuario.setFechaNacimiento(calendar.getTimeInMillis());
		usuario.setPeso(Double.valueOf(pesoMod.getText().toString()));
		usuario.setEstatura(Double.valueOf(estaturaMod.getText().toString()));
		usuario.setProvincia(spinnerProvincias.getSelectedItem().toString());
		usuario.setCanton(spinnerCantones.getSelectedItem().toString());
		usuario.setDistrito(spinnerDistritos.getSelectedItem().toString());
		usuario.setContrasena(contrasenaMod.getText().toString());

		int AffectedRow = Controller.obtenerInstancia().actualizarUsuario(usuario, this);
		if (AffectedRow != 0) {
			Toast.makeText(this, "El usuario se ha modificado correctamente", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "No se pudo modificar el usuario", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void cargarProvincias() {
		ArrayList<Provincia> provincias = Controller.obtenerInstancia().getProvincias();

		String[] datos = new String[provincias.size()];

		for (int i = 0; i < provincias.size(); i++) {
			datos[i] = provincias.get(i).getNombreProvincia();
			if (inicio == 1) {
				if (datos[i].equals(Controller.obtenerInstancia().getUsuario().getProvincia())) {
					pos = i;
				}
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinnerProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ejecutarWebService(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm2-cantones.json",
						"2", String.valueOf((inicio == 0) ? position + 1 : pos + 1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerProvincias.setAdapter(adapter);
		if (inicio == 1) {
			spinnerProvincias.setSelection(pos);
		}
	}

	@Override
	public void cargarCantones() {
		ArrayList<Canton> cantones = Controller.obtenerInstancia().getCantones();

		final String[] datos = new String[cantones.size()];

		for (int i = 0; i < cantones.size(); i++) {
			datos[i] = cantones.get(i).getNombreCanton();
			if (inicio == 1) {
				if (datos[i].equals(Controller.obtenerInstancia().getUsuario().getCanton())) {
					pos = i;
				}
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				datos);

		spinnerCantones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				ejecutarWebService(
						"https://raw.githubusercontent.com/investigacion/divisiones-territoriales-data/master/data/json/adm3-distritos.json",
						"3", Controller.obtenerInstancia().getIdCanton(datos[(inicio == 0) ? position : pos]));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinnerCantones.setAdapter(adapter);
		if (inicio == 1) {
			spinnerCantones.setSelection(pos);
		}
	}

	@Override
	public void cargarDistritos() {
		ArrayList<Distrito> distritos = Controller.obtenerInstancia().getDistritos();

		final String[] datos = new String[distritos.size()];

		for (int i = 0; i < distritos.size(); i++) {
			datos[i] = distritos.get(i).getNombreDistrito();
			if (inicio == 1) {
				if (datos[i].equals(Controller.obtenerInstancia().getUsuario().getDistrito())) {
					pos = i;
				}
			}
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
		if (inicio == 1) {
			spinnerDistritos.setSelection(pos);
			inicio = 0;
		}
	}

}
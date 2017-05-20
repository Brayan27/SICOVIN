package com.programa.sicovin;

import java.util.Calendar;

import com.programa.controller.Controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends Activity {

	// https://www.youtube.com/watch?v=fH3S6HbbZ1k
	private Button botonIrInicio;
	private Button botonVacunas;

	// RadioButton y textView of Table
	private RadioButton radioButton;
	private TextView textView1Tab;
	private DatePickerDialog datePickerDialog;

	// Guardar vacuna aplicación
	private int vacuna = 0;
	private int calendario = 0;
	private long fechaAplicacion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		inicializarComponentes();
		ajustarEventos();

	}

	private void inicializarComponentes() {
		Controller.obtenerInstancia().cargarTablaVacuna(this);
		Controller.obtenerInstancia().cargarTablaCalendarioVacunacion(this);
		Controller.obtenerInstancia().cargarTablaUsuarioCalendario(this);

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

	public void onRadioButtonClicked(View view) {
		radioButton = (RadioButton) findViewById(view.getId());
		// showDialog(DATE_DIALOG_ID);
		switch (view.getId()) {
		case R.id.radioButton1:
			vacuna = 1;
			calendario = 0;
			textView1Tab = (TextView) findViewById(R.id.textView1Tab);
			break;
		case R.id.radioButton2:
			vacuna = 2;
			calendario = 0;
			textView1Tab = (TextView) findViewById(R.id.textView2Tab);
			break;
		case R.id.radioButton3:
			vacuna = 2;
			calendario = 2;
			textView1Tab = (TextView) findViewById(R.id.textView3Tab);
			break;
		case R.id.radioButton4:
			vacuna = 2;
			calendario = 6;
			textView1Tab = (TextView) findViewById(R.id.textView4Tab);
			break;
		case R.id.radioButton5:
			vacuna = 3;
			calendario = 2;
			textView1Tab = (TextView) findViewById(R.id.textView5Tab);
			break;
		case R.id.radioButton6:
			vacuna = 3;
			calendario = 4;
			textView1Tab = (TextView) findViewById(R.id.textView6Tab);
			break;
		case R.id.radioButton7:
			vacuna = 3;
			calendario = 6;
			textView1Tab = (TextView) findViewById(R.id.textView7Tab);
			break;
		case R.id.radioButton8:
			vacuna = 3;
			calendario = 15;
			textView1Tab = (TextView) findViewById(R.id.textView8Tab);
			break;
		case R.id.radioButton9:
			vacuna = 4;
			calendario = 2;
			textView1Tab = (TextView) findViewById(R.id.textView9Tab);
			break;
		case R.id.radioButton10:
			vacuna = 4;
			calendario = 4;
			textView1Tab = (TextView) findViewById(R.id.textView10Tab);
			break;
		case R.id.radioButton11:
			vacuna = 4;
			calendario = 6;
			textView1Tab = (TextView) findViewById(R.id.textView11Tab);
			break;
		case R.id.radioButton12:
			vacuna = 4;
			calendario = 15;
			textView1Tab = (TextView) findViewById(R.id.textView12Tab);
			break;
		case R.id.radioButton13:
			vacuna = 4;
			calendario = 48;
			textView1Tab = (TextView) findViewById(R.id.textView13Tab);
			break;
		case R.id.radioButton14:
			vacuna = 4;
			calendario = 120;
			textView1Tab = (TextView) findViewById(R.id.textView14Tab);
			break;
		case R.id.radioButton15:
			vacuna = 5;
			calendario = 2;
			textView1Tab = (TextView) findViewById(R.id.textView15Tab);
			break;
		case R.id.radioButton16:
			vacuna = 5;
			calendario = 4;
			textView1Tab = (TextView) findViewById(R.id.textView16Tab);
			break;
		case R.id.radioButton17:
			vacuna = 5;
			calendario = 6;
			textView1Tab = (TextView) findViewById(R.id.textView17Tab);
			break;
		case R.id.radioButton18:
			vacuna = 5;
			calendario = 48;
			textView1Tab = (TextView) findViewById(R.id.textView18Tab);
			break;
		case R.id.radioButton19:
			vacuna = 6;
			calendario = 15;
			textView1Tab = (TextView) findViewById(R.id.textView19Tab);
			break;
		case R.id.radioButton20:
			vacuna = 6;
			calendario = 84;
			textView1Tab = (TextView) findViewById(R.id.textView20Tab);
			break;
		case R.id.radioButton21:
			vacuna = 7;
			calendario = 15;
			textView1Tab = (TextView) findViewById(R.id.textView21Tab);
			break;
		case R.id.radioButton22:
			vacuna = 8;
			calendario = 2;
			textView1Tab = (TextView) findViewById(R.id.textView22Tab);
			break;
		case R.id.radioButton23:
			vacuna = 8;
			calendario = 4;
			textView1Tab = (TextView) findViewById(R.id.textView23Tab);
			break;
		case R.id.radioButton24:
			vacuna = 8;
			calendario = 6;
			textView1Tab = (TextView) findViewById(R.id.textView24Tab);
			break;
		case R.id.radioButton25:
			vacuna = 8;
			calendario = 15;
			textView1Tab = (TextView) findViewById(R.id.textView25Tab);
			break;
		}
		if (Controller.obtenerInstancia().validarVacuna(calendario)) {
			mostrarDatePickerDialog();
		} else {
			mensajeToast("Esta vacuna no puede ser aplicada al menor debido a que no cuenta con la edad mínima.");
			radioButton.setChecked(false);
		}
	}

	private void mostrarDatePickerDialog() {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day);
		datePickerDialog.getDatePicker().setCalendarViewShown(false);
		datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
		datePickerDialog.setTitle("Seleccione la fecha de vacunación");
		datePickerDialog.show();
	}

	final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			guardarVacuna(view);
		}
	};

	public void guardarVacuna(DatePicker datepickerGuardar) {

		int day = datepickerGuardar.getDayOfMonth();
		int month = datepickerGuardar.getMonth();
		int year = datepickerGuardar.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		fechaAplicacion = calendar.getTimeInMillis();

		if (Controller.obtenerInstancia().aplicarVacunaUsuario(this, vacuna, calendario, fechaAplicacion)) {
			mensajeToast("Exito al registrar la vacuna para el menor.");
			textView1Tab.setText("       X");
		} else {
			mensajeToast("La vacuna ya se encuentra registrada para el menor.");
		}
	}

	private void mensajeToast(String mensaje) {
		Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
	}
}

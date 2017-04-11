package com.programa.sicovin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private CheckBox menorAnio;
	private TextView textEdad;
	private EditText reg_edad;
	private EditText reg_edadMeses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		inicializarComponentes();
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
		menorAnio = (CheckBox) findViewById(R.id.checkBoxMenor);
		textEdad = (TextView) findViewById(R.id.textEdad);
		textEdad.setText("Edad en Años");
		reg_edad = (EditText) findViewById(R.id.reg_edad);
		reg_edadMeses = (EditText) findViewById(R.id.reg_edadMeses);
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

}

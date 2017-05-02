package com.programa.sicovin;

import com.programa.controller.Controller;
import com.programa.model.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button buttonLogin;
	private TextView textReg;
	private EditText cedula;
	private EditText contrasena;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		cedula = (EditText) findViewById(R.id.iniCedula);
		contrasena = (EditText) findViewById(R.id.iniContrasena);
		buttonLogin = (Button) findViewById(R.id.btnLogin);
		textReg = (TextView) findViewById(R.id.link_register);
	}

	private void ajustarEventos() {
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				iniciarSesion();
			}
		});
		textReg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
	}

	private void iniciarSesion() {
		try {
			Usuario usuario = new Usuario();
			usuario.setCedula(cedula.getText().toString());
			usuario.setContrasena(contrasena.getText().toString());
			Controller.obtenerInstancia().iniciarSesion(usuario, this);

			if (Controller.obtenerInstancia().getUsuario() != null) {
				Toast.makeText(this, "Éxito al iniciar sesión", Toast.LENGTH_SHORT).show();
				cargarVacunas();
				Intent i = new Intent(getApplicationContext(), InicioActivity.class);
				startActivity(i);

			} else {
				Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception ex) {
			Log.e(null, ex.getMessage());
			Log.e(null, ex.getCause().toString());
		}
	}

	private void cargarVacunas() {

		try {
			Controller.obtenerInstancia().cargarTablaVacuna(this);
			Toast.makeText(this, "Se cargaron las vacunas", Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Log.e("Error", ex.getMessage());
		}

	}

}

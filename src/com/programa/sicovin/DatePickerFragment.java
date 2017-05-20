package com.programa.sicovin;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the date picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new DatePickerDialog instance and return it
		/*
		 * DatePickerDialog Public Constructors - Here we uses first one public
		 * DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener
		 * callBack, int year, int monthOfYear, int dayOfMonth) public
		 * DatePickerDialog (Context context, int theme,
		 * DatePickerDialog.OnDateSetListener listener, int year, int
		 * monthOfYear, int dayOfMonth)
		 */
		DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		dialog.getDatePicker().setCalendarViewShown(false);
		dialog.getDatePicker().setCalendarViewShown(false);
		dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
		dialog.setTitle("Seleccione la fecha de vacunación.");

		return dialog;
	}
	
	

	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		// TextView tv = (TextView) getActivity().findViewById(R.id.tv);
		// tv.setText("Date changed...");
		// tv.setText(tv.getText() + "\nYear: " + year);
		// tv.setText(tv.getText() + "\nMonth: " + month);
		// tv.setText(tv.getText() + "\nDay of Month: " + day);

		String stringOfDate = day + "/" + month + "/" + year;
		Toast.makeText(getActivity(), stringOfDate, Toast.LENGTH_SHORT).show();
		// tv.setText(tv.getText() + "\n\nFormatted date: " + stringOfDate);
	}
}
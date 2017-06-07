package com.example.ediloaz.control07.Citas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.dbEnfermedadesEditar;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesEditar;
import com.example.ediloaz.control07.R;

import java.util.Calendar;

public class ActivityCitasEditar extends CommonCode {
    private TextView edit_fecha_cita, edit_hora_cita;
    private Button button_editar, button_fecha, button_hora;
    private ProgressBar progressBar;
    private String fecha, hora;
    private int hora2, minutos,tiempo;
    private int id;
    private Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_editar);

        button_fecha = (Button)findViewById(R.id.button_SeleccionarFecha);

        button_fecha.setOnClickListener(this);

        button_hora = (Button)findViewById(R.id.button_SeleccionarHora);
        button_hora.setOnClickListener(this);

        edit_fecha_cita = (TextView)findViewById(R.id.text_CitaFecha);
        edit_hora_cita = (TextView)findViewById(R.id.text_CitaHora);

        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_editar = (Button)findViewById(R.id.button_EditarCita_editar);
        button_editar.setOnClickListener(this);


        fecha = getIntent().getStringExtra("fecha");
        hora = getIntent().getStringExtra("hora");
        id =  getIntent().getIntExtra("id",0);


        edit_fecha_cita.setText(fecha);
        edit_hora_cita.setText(hora);


    }
    DatePickerDialog.OnDateSetListener listener_date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
          edit_fecha_cita.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);

        }
    };


    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_SeleccionarFecha:
                new DatePickerDialog(ActivityCitasEditar.this,listener_date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            break;

            case R.id.button_SeleccionarHora:
                hora2=calendar.get(Calendar.HOUR_OF_DAY);
                minutos=calendar.get(Calendar.MINUTE);
                tiempo = calendar.get(Calendar.AM_PM);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
                    @Override

                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        edit_hora_cita.setText(hourOfDay+ ":"+ minute + ":"+"00");

                    }
                },hora2,minutos,false);
                timePickerDialog.show();
                break;

            case R.id.button_EditarCita_editar:

                fecha = edit_fecha_cita.getText().toString();
                hora = edit_hora_cita.getText().toString();

                dbCitasEditar db = new dbCitasEditar(this,progressBar,fecha,hora,id);

                db.execute();
                break;
        }
    }
}

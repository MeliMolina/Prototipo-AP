package com.example.ediloaz.control07.Citas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.app.TimePickerDialog;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Estadisticas.Activity_Estadisticas;
import com.example.ediloaz.control07.R;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Administrador on 03/06/2017.
 */

public class Activity_Citas_Recordatorio extends CommonCode {

    private PendingIntent pendingIntent;
    private Button button_aceptar, button_seleccionar_hora;
    private int dias;

    private Activity_Citas_Recordatorio activity;
    private int minutos, segundos,hora,mes,dia,anio;
    private boolean correctFinished;
    private Date hora_de_envio;

    private Calendar calendar = Calendar.getInstance();
    private int tiempo;
    private TextView hora_cita;
    private Spinner spinner_dias;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_recordatorio);

        button_aceptar = (Button)findViewById(R.id.button_Aceptar);
        button_aceptar.setOnClickListener(this);

        button_seleccionar_hora = (Button)findViewById(R.id.button_SeleccionarHora);
        button_seleccionar_hora.setOnClickListener(this);

        spinner_dias = (Spinner)findViewById(R.id.spinner_dias);

        hora_cita = (TextView)findViewById(R.id.text_Hora);

        super.Listener();

        llenarSpinner();

    }
    //@Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_SeleccionarHora:

                hora = calendar.get(Calendar.HOUR_OF_DAY);
                minutos = calendar.get(Calendar.MINUTE);
                segundos = calendar.get(Calendar.SECOND);
                tiempo = calendar.get(Calendar.AM_PM);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override

                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora_cita.setText(hourOfDay + ":" + minute + ":" + "00");

                    }
                }, hora, minutos, false);
                timePickerDialog.show();

                setHora(hora);
                setMinutos(minutos);
                setSegundos(segundos);
                setTiempo(tiempo);

                break;

            case R.id.button_Aceptar:
                correctFinished = false;
                //dias =  (Integer)spinner_dias.getSelectedItem();

                /*calendar.set(Calendar.MONTH, GetMes());
                calendar.set(Calendar.YEAR, GetAnio());
                calendar.set(Calendar.DAY_OF_MONTH, GetDia());*/

                calendar.set(Calendar.HOUR_OF_DAY, GetHora());
                calendar.set(Calendar.MINUTE, GetMinutos());
                calendar.set(Calendar.SECOND, GetSegundos());
                calendar.set(Calendar.AM_PM, GetTiempo());

                hora_de_envio = calendar.getTime();
                int tiempoRepeticion = 86400000;
                Timer temporizador = new Timer();
                Log.v("Activity_Recordatorio","Entrando a temporizador");
                temporizador.schedule(new Temporizador(), hora_de_envio, tiempoRepeticion);
                //dbCitasEditar db = new dbCitasEditar(this,progressBar,fecha,hora,id);
                correctFinished = true;

                //FragmentManager fragmentManager = getSupportFragmentManager();

                //Dialogo_aviso dialogo = new Dialogo_aviso().newInstance(hora_de_envio, correctFinished);
                //dialogo.show(fragmentManager, "tagAlerta");
                break;

        }
    }


    public void setHora(int phora){
        this.hora = phora;
    }

    public int GetHora(){
        return hora;
    }


    public void setMinutos(int pminutos){
        this.minutos = pminutos;

    }

    public int GetMinutos(){
        return minutos;

    }
    public void setSegundos(int psegundos){
        this.segundos = psegundos;

    }

    public int GetSegundos(){
        return segundos;

    }

    public void setTiempo(int ptiempo){
        this.tiempo = ptiempo;

    }

    public int GetTiempo(){
        return tiempo;

    }


    public void setMes(int pmes){
        this.mes = pmes;

    }

    public int GetMes(){
        return mes;

    }

    public void setAnio(int panio){
        this.anio = panio;

    }

    public int GetAnio(){
        return anio;

    }




    public void llenarSpinner(){
        try {
            List<Integer> list = new ArrayList<Integer>();
            for(int i = 1; i <= 31; i++) {
                list.add(i);
            }

            ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_dias.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), Activity_Citas_Recordatorio.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }

}
package com.example.ediloaz.control07.Citas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.GregorianCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityCitasInicio extends CommonCode {
    Button button_new, button_citas_fecha, button_buscarcitas;
    private String fecha;
    TableLayout tablapos;
    TextView fecha_cita;
    private String fecha_sistema;

    private ProgressBar progressBar;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_inicio);

        super.Listener();
        button_new = (Button) findViewById(R.id.button_Citas_Nuevo);
        button_new.setOnClickListener(this);

        button_citas_fecha= (Button) findViewById(R.id.button_Citas_Por_Fecha);
        button_citas_fecha.setOnClickListener(this);

        button_buscarcitas= (Button) findViewById(R.id.button_buscar_citas);
        button_buscarcitas.setOnClickListener(this);


        tablapos = (TableLayout) findViewById(R.id.table_Citas_list);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        fecha_cita = (TextView)findViewById(R.id.text_Fecha_Cita);

    }

    DatePickerDialog.OnDateSetListener listener_date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

            if(monthOfYear < 10) {
                fecha_cita.setText(year + "-" + "0"+(monthOfYear + 1) + "-" + dayOfMonth);
            }
            else{
                fecha_cita.setText(year + "-" +(monthOfYear + 1) + "-" + dayOfMonth);

            }

        }
    };


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //String date = getFechaActual();

        dbCitasPorFechaActual db = new dbCitasPorFechaActual(this, progressBar,"");
        db.execute("");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_Citas_Nuevo:
                Intent intent_CitasNuevo = new Intent(getApplicationContext(), ActivityCitasNuevo.class);
                startActivity(intent_CitasNuevo);
                break;

            case R.id.button_Citas_Por_Fecha:
                new DatePickerDialog(ActivityCitasInicio.this,listener_date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                fecha = fecha_cita.getText().toString();
                dbCitasPorFecha db = new dbCitasPorFecha(this,progressBar,fecha);
                db.execute();
                break;

            case R.id.button_buscar_citas:
                Intent intent_BuscarCitas = new Intent(getApplicationContext(), ActivityBuscarCitasVista.class);
                startActivity(intent_BuscarCitas);

                break;
        }
    }

    public void llenarTabla(ArrayList<Cita> matriz_datos){

        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }

        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);
            Button   button_mostrar_cita = new Button(this);
            Button   button_editar_cita = new Button(this);

            final Cita cita = matriz_datos.get(i);


            tv1.setText(cita.getFecha());
            tv2.setText(cita.getHora());

            tv3.setText(cita.getMedico().getNombre());
            tv4.setText(cita.getPaciente().getNombre());

            button_mostrar_cita.setText("Mostrar");
            button_editar_cita.setText("Editar");

            button_mostrar_cita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasInicio.this, ActivityCitasVista.class);

                    intent.putExtra("id", cita.getId());
                    intent.putExtra("fecha", cita.getFecha());
                    intent.putExtra("hora", cita.getHora());
                    intent.putExtra("medico",cita.getMedico().getNombre());
                    intent.putExtra("paciente",cita.getPaciente().getNombre());
                    intent.putExtra("apellido1paciente",cita.getPaciente().getApellido1());
                    intent.putExtra("apellido2paciente",cita.getPaciente().getApellido2());
                    intent.putExtra("apellido1medico",cita.getMedico().getApellido1());
                    intent.putExtra("apellido2medico",cita.getMedico().getApellido2());
                    startActivity(intent);
                }
            });

            button_editar_cita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasInicio.this, ActivityCitasEditar.class);

                    intent.putExtra("id", cita.getId());
                    intent.putExtra("fecha", cita.getFecha());
                    intent.putExtra("hora", cita.getHora());
                    startActivity(intent);
                }
            });


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar_cita.setAllCaps(false);
                button_editar_cita.setAllCaps(false);
            }
            tv1.setTextSize(16);
            tv2.setTextSize(16);
            tv3.setTextSize(16);
            tv4.setTextSize(16);
            fila.addView(tv1);
            fila.addView(tv2);
            fila.addView(tv3);
            fila.addView(tv4);
            fila.addView(button_mostrar_cita);
            fila.addView(button_editar_cita);
            fila.setPadding(0,10,0,10);


            tablapos.addView(fila);
        }

    }
}


package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 18/05/2017.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityBuscarCitasVista extends CommonCode {

    private Button button_search;
    private TableLayout tablapos;
    private Spinner spinner_nombre, spinner_apellido1,spinner_apellido2;
    private ArrayList<Paciente> matriz_datos_list;
    private Paciente paciente;


    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_citas);
        super.Listener();
        tablapos = (TableLayout)findViewById(R.id.table_Citas_list);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_search = (Button)findViewById(R.id.button_Buscar_Citas);
        button_search.setOnClickListener(this);

        spinner_nombre = (Spinner)findViewById(R.id.spinner_nombre_Paciente_list);
        spinner_apellido1 = (Spinner)findViewById(R.id.spinner_apellido1_list);
        spinner_apellido2= (Spinner)findViewById(R.id.spinner_apellido2_list);
        llenarSpinnerNombre();
        llenarSpinnerApellido1();
        llenarSpinnerApellido2();


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_Buscar_Citas:

                String nombre = String.valueOf(spinner_nombre.getSelectedItem());
                String apellido1 = String.valueOf(spinner_apellido1.getSelectedItem());
                String apellido2 = String.valueOf(spinner_apellido2.getSelectedItem());

               Log.i("_A_A_A_A_A_A_A_A_A_",nombre);
               Log.i("_A_A_A_A_A_A_A_A_A_",apellido1);
               Log.i("_A_A_A_A_A_A_A_A_A_",apellido2);
                dbCitasPaciente db = new dbCitasPaciente(this,progressBar,nombre,apellido1,apellido2);
                db.execute("");

                Log.i("_A_A_A_A_A_A_A_A_A_","3");
                Log.i("_A_A_A_A_A_A_A_A_A_","4");
                Log.i("_A_A_A_A_A_A_A_A_A_","5");

                break;

        }
    }

    public void llenarTabla(ArrayList<Cita> matriz_datos){
        Log.i("_A_A_A_A_A_A_A_A_A_","6");
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        Log.i("_A_A_A_A_A_A_A_A_A_","7");
        for(int i=0; i<matriz_datos.size(); i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);


            /*Button   button_mostrar = new Button(this);
            Button   button_editar = new Button(this);
            Button   button_eliminar = new Button(this);*/

            final Cita cita = matriz_datos.get(i);

            Log.i("_A_A_A_A_A_A_A_A_A_","8");

            tv1.setText(matriz_datos.get(i).getFecha());
            tv2.setText(matriz_datos.get(i).getHora());

           /* button_mostrar.setText("Mostrar");
            button_editar.setText("Editar");
            button_eliminar.setText("Eliminar");*/

            /*button_mostrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityBuscarCitasVista.this, ActivityCitasVista.class);

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

            button_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityBuscarCitasVista.this, ActivityCitasEditar.class);

                    intent.putExtra("id", cita.getId());
                    intent.putExtra("fecha", cita.getFecha());
                    intent.putExtra("hora", cita.getHora());
                    startActivity(intent);
                }
            });*/

            /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_mostrar.setAllCaps(false);
                button_editar.setAllCaps(false);
                button_eliminar.setAllCaps(false);
            }*/


            tv1.setTextSize(16);
            tv2.setTextSize(16);
            fila.addView(tv1);
            fila.addView(tv2);
           /* fila.addView(button_mostrar);
            fila.addView(button_editar);
            fila.addView(button_eliminar);*/
            fila.setPadding(0,10,0,10);

            tablapos.addView(fila);
        }
    }

    public void llenarSpinnerNombre(){
        try {
            dbNombrePacientesSpinner db = new dbNombrePacientesSpinner(-1);
            db.execute("").get();

            matriz_datos_list = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_list.size(); i++){
                string_temp = matriz_datos_list.get(i).getNombre().toString();
                list.add(string_temp);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nombre.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityBuscarCitasVista.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }


    public void llenarSpinnerApellido1(){
        try {
            dbApellido1PacientesSpinner db = new dbApellido1PacientesSpinner(-1);
            db.execute("").get();

            matriz_datos_list = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_list.size(); i++){
                string_temp = matriz_datos_list.get(i).getApellido1().toString();
                list.add(string_temp);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_apellido1.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityBuscarCitasVista.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }

    public void llenarSpinnerApellido2(){
        try {
            dbApellido2PacientesSpinner db = new dbApellido2PacientesSpinner(-1);
            db.execute("").get();

            matriz_datos_list = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_list.size(); i++){
                string_temp = matriz_datos_list.get(i).getApellido2().toString();
                list.add(string_temp);
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_apellido2.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityBuscarCitasVista.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
}

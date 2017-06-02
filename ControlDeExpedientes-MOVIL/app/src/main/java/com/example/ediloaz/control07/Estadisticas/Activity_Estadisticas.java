package com.example.ediloaz.control07.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.example.ediloaz.control07.Citas.ActivityBuscarCitasVista;
import com.example.ediloaz.control07.Citas.dbCitasPaciente;
import com.example.ediloaz.control07.Citas.dbNombrePacientesSpinner;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/06/2017.
 */

public class Activity_Estadisticas extends CommonCode {
    private Spinner spinner_estadisticas;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        super.Listener();
        spinner_estadisticas = (Spinner)findViewById(R.id.spinner_estadistica);
        llenarSpinner();

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_Buscar_Citas:

                String item = String.valueOf(spinner_estadisticas.getSelectedItem());
                if(item == "Enfermedades por paciente"){
                    Intent intent_ = new Intent(this.getApplicationContext(), Activity_Estadisticas.class);
                    startActivity(intent_Ingresar);
                    finish();

                }


                break;

        }
    }
    public void llenarSpinner(){
        try {
            List<String> list = new ArrayList<String>();

                list.add("Enfermedades por paciente");
                list.add("Enfermedades por género");
                list.add("Enfermedades por provincia");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_estadisticas.setAdapter(dataAdapter);
        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), Activity_Estadisticas.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }
}
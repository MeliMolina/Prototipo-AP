package com.example.ediloaz.control07.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.ediloaz.control07.Citas.ActivityBuscarCitasVista;
import com.example.ediloaz.control07.Citas.ActivityCitasInicio;
import com.example.ediloaz.control07.Citas.Activity_Citas_Recordatorio;
import com.example.ediloaz.control07.Citas.dbCitasPaciente;
import com.example.ediloaz.control07.Citas.dbNombrePacientesSpinner;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;
import com.example.ediloaz.control07.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/06/2017.
 */

public class Activity_Estadisticas extends CommonCode {
    private Spinner spinner_estadisticas;
    ImageButton estadistica_paciente;
    ImageButton estadistica_lugar;
    ImageButton estadistica_genero;
    private SessionManager session;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        super.Listener();

        session = new SessionManager(this.getApplicationContext());

        estadistica_paciente =(ImageButton)findViewById(R.id.button_estadisticas_paciente);
        estadistica_paciente.setOnClickListener(this);

        estadistica_lugar =(ImageButton)findViewById(R.id.button_estadisticas_lugar);
        estadistica_lugar.setOnClickListener(this);

        estadistica_genero =(ImageButton)findViewById(R.id.button_estadisticas_genero);
        estadistica_paciente.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        boolean admin = getSession().isAdmin();
        switch (v.getId()) {
            case R.id.dashboard_Pacientes:
                Intent intent_Pacientes = new Intent(getApplicationContext(),ActivityPacientesInicio.class);
                startActivity(intent_Pacientes);
                finish();
                break;
            case R.id.dashboard_Medicos:
                if(admin){
                    Intent intent_Medicos = new Intent(getApplicationContext(),ActivityMedicosInicio.class);
                    startActivity(intent_Medicos);
                    finish();
                }else{
                    Toast.makeText(this.getApplicationContext(),"Ingreso para administradores.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dashboard_Enfermedades:
                if(admin){
                    Intent intent_Enfermedades= new Intent(getApplicationContext(),ActivityEnfermedadesInicio.class);
                    startActivity(intent_Enfermedades);
                    finish();
                }else{
                    Toast.makeText(this.getApplicationContext(),"Ingreso para administradores.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dashboard_Citas:
                Intent intent_Citas = new Intent(getApplicationContext(),ActivityCitasInicio.class);
                startActivity(intent_Citas);
                finish();
                break;
            case R.id.dashboard_LogOut:
                if(getSession().isLoggedIn()){
                    getSession().logoutUser();
                    finish();
                }
                break;

            case R.id.dashboard_Gráficas:
                Intent intent_Graficas = new Intent(getApplicationContext(), Activity_Estadisticas.class);
                startActivity(intent_Graficas);
                finish();
                break;

            case R.id.dashboard_Recordatorio:
                if(admin) {
                    Intent intent_Recordatorio = new Intent(getApplicationContext(), Activity_Citas_Recordatorio.class);
                    startActivity(intent_Recordatorio);
                    finish();
                }
                else{
                Toast.makeText(this.getApplicationContext(),"Ingreso para administradores.", Toast.LENGTH_SHORT).show();
            }
                break;

            case R.id.button_estadisticas_paciente:
                Intent intent_estadistica_paciente = new Intent(this.getApplicationContext(), Activity_PacientesPorEnfermedad.class);
                startActivity(intent_estadistica_paciente);
                finish();

                break;

            case R.id.button_estadisticas_genero:
                Intent intent_estadistica_genero = new Intent(this.getApplicationContext(), Activity_Mujeres_Hombres.class);
                startActivity(intent_estadistica_genero);
                finish();

                break;

            case R.id.button_estadisticas_lugar:
                Intent intent_estadistica_lugar = new Intent(this.getApplicationContext(), Activity_Estadisticas_Por_Lugar.class);
                startActivity(intent_estadistica_lugar);
                finish();

                break;
        }
    }
    //@Override
    /*public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_estadisticas:

                String item = String.valueOf(spinner_estadisticas.getSelectedItem());
                if(item == "Enfermedades por paciente"){
                    Intent intent_estadistica = new Intent(this.getApplicationContext(), Activity_PacientesPorEnfermedad.class);
                    startActivity(intent_estadistica);
                    finish();



                }
                if(item == "Enfermedades por género"){
                    Intent intent_estadistica = new Intent(this.getApplicationContext(), Activity_Mujeres_Hombres.class);
                    startActivity(intent_estadistica);
                    finish();
                }
                else{
                    Intent intent_estadistica = new Intent(this.getApplicationContext(), Activity_Estadisticas_Por_Lugar.class);
                    startActivity(intent_estadistica);
                    finish();
                }


                break;

        }
    }*/
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
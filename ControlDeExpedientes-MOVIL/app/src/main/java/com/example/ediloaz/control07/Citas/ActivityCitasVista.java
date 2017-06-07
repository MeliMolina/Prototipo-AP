package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionesInicio;
import com.example.ediloaz.control07.R;

/**
 * Created by Administrador on 14/04/2017.
 */

public class ActivityCitasVista extends CommonCode {
    private String paciente_nombre;
    private String medico_nombre;
    private Button correo;
    private String fecha, hora, medico, paciente, apellido1paciente, apellido2paciente, apellido1medico, apellido2medico;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_vista);
        super.Listener();

        fecha = getIntent().getStringExtra("fecha");
        hora = getIntent().getStringExtra("hora");
        medico = getIntent().getStringExtra("medico");
        paciente = getIntent().getStringExtra("paciente");
        apellido1paciente = getIntent().getStringExtra("apellido1paciente");
        apellido2paciente = getIntent().getStringExtra("apellido2paciente");
        apellido1medico = getIntent().getStringExtra("apellido1medico");
        apellido2medico = getIntent().getStringExtra("apellido2medico");



         id = getIntent().getIntExtra("id",0);

        int activo = getIntent().getIntExtra("activo",0);

        paciente_nombre = paciente + " " + apellido1paciente + " " + apellido2paciente;

        if(medico == "Administrador"){
            medico_nombre = medico;
        }
        else{
            medico_nombre = medico + " " + apellido1medico + " " + apellido2medico;
        }

        TextView txtPaciente = (TextView) findViewById(R.id.text_data_RegistrarPaciente_name);
        TextView txtMedico = (TextView) findViewById(R.id.text_data_RegistrarMedico_name);
        TextView txtFecha = (TextView) findViewById(R.id.text_data_RegistrarCita_date);
        TextView txtHora = (TextView) findViewById(R.id.text_data_RegistrarCita_hour);




        txtPaciente.setText(paciente_nombre);
        txtMedico.setText(medico_nombre);
        txtFecha.setText(fecha);
        txtHora.setText(hora);

    }


}
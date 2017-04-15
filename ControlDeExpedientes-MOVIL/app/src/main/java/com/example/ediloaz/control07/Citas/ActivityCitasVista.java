package com.example.ediloaz.control07.Citas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

/**
 * Created by Administrador on 14/04/2017.
 */

public class ActivityCitasVista extends CommonCode {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_vista);
        super.Listener();

        String fecha = getIntent().getStringExtra("fecha");
        String hora = getIntent().getStringExtra("hora");
        String medico = getIntent().getStringExtra("medico");
        String paciente = getIntent().getStringExtra("paciente");


        int id = getIntent().getIntExtra("id",0);

        int activo = getIntent().getIntExtra("activo",0);

        TextView txtMedico = (TextView) findViewById(R.id.text_data_RegistarMedico_nombre);
        TextView txtPaciente = (TextView) findViewById(R.id.text_data_RegistarPaciente_nombre);
        TextView txtFecha = (TextView) findViewById(R.id.text_data_RegistarCitas_fecha);
        TextView txtHora = (TextView) findViewById(R.id.text_data_RegistarCitas_hora);


        txtMedico.setText(medico);
        txtPaciente.setText(paciente);
        txtFecha.setText(fecha);
        txtHora.setText(hora);


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}

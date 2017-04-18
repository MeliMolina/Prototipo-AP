package com.example.ediloaz.control07.Citas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.dbEnfermedadesEditar;
import com.example.ediloaz.control07.R;

public class ActivityCitasEditar extends CommonCode {
    private EditText edit_fecha_cita, edit_hora_cita;
    private Button button_editar;
    private ProgressBar progressBar;
    private String fecha, hora ;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_editar);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_editar = (Button)findViewById(R.id.button_EditarCita_editar);
        button_editar.setOnClickListener(this);


        edit_fecha_cita =  (EditText) findViewById(R.id.edit_RegistrarCita_date);
        edit_hora_cita =  (EditText) findViewById(R.id.edit_RegistrarCita_hour);

        fecha = getIntent().getStringExtra("fecha");
        hora = getIntent().getStringExtra("hora");
        id =  getIntent().getIntExtra("id",0);

        edit_fecha_cita.setText(fecha);
        edit_hora_cita.setText(hora);


    }
    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_EditarCita_editar:

                fecha = edit_fecha_cita.getText().toString();
                hora = edit_hora_cita.getText().toString();

                dbCitasEditar db = new dbCitasEditar(this,progressBar,fecha,hora,id);

                db.execute();
                break;
        }
    }
}

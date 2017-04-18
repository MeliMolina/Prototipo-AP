package com.example.ediloaz.control07.Citas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Medicos.dbMedicosNuevo;
import com.example.ediloaz.control07.R;

public class ActivityCitasNuevo2 extends CommonCode {
    Button button_agregar;
    private EditText edit_fecha, edit_hora;
    private String fecha, hora;
    private int id;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo2);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_agregar = (Button)findViewById(R.id.button_CitasNuevo2_Agregar);
        button_agregar.setOnClickListener(this);

        edit_fecha =  (EditText) findViewById(R.id.edit_CitasNuevo2_fecha_answer);
        edit_hora =  (EditText) findViewById(R.id.edit_CitasNuevo2_hora_answer);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_CitasNuevo2_Agregar:

                fecha = edit_fecha.getText().toString();
                hora= edit_hora.getText().toString();

                dbCitasNuevo db = new dbCitasNuevo(this,progressBar,fecha,hora,id);

                db.execute();
                break;
        }
    }
}

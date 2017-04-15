package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityEmpty;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesBuscar;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesNuevo;
import com.example.ediloaz.control07.Enfermedades.dbListadoEnfermedades;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityCitasInicio extends CommonCode {
    Button button_search,button_new;
    TableLayout tablapos;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_inicio);

        super.Listener();
        button_new = (Button) findViewById(R.id.button_Citas_Nuevo);
        button_new.setOnClickListener(this);
        tablapos = (TableLayout) findViewById(R.id.table_Citas_list);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dbCitasInicio db = new dbCitasInicio(this, progressBar,"");
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


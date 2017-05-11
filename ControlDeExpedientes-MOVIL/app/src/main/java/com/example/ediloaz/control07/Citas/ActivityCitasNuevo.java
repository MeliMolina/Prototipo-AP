package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.Pacientes.dbPacientesListado;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;

public class ActivityCitasNuevo extends CommonCode {
    Button button_buscar;
    TableLayout tablapos;
    EditText edit_buscar;
    String[][] matriz_datos, matriz_datos_list;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        edit_buscar =  (EditText) findViewById(R.id.edit_CitasNuevo_Buscar);
        button_buscar = (Button)findViewById(R.id.button_CitasNuevo_Buscar);
        button_buscar.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_CitasBuscar_list);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_CitasNuevo_Buscar:
                tablapos.removeAllViews();
                Log.i("_A_A_A_A_A_A_A_A_A_", "10");
                String texto_a_buscar = edit_buscar.getText().toString();
                Log.i("_A_A_A_A_A_A_A_A_A_", "11"+texto_a_buscar);
                dbListadoPacientesCitas db = new dbListadoPacientesCitas(this, progressBar,texto_a_buscar);
                Log.i("_A_A_A_A_A_A_A_A_A_", "12");
                InputMethodManager imm = (InputMethodManager)getSystemService(this.getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_buscar.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                db.execute();
                Log.i("_A_A_A_A_A_A_A_A_A_", "3");
                break;
        }
    }


    public void llenarTabla(ArrayList<Paciente> matriz_datos){
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));

            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button button_agregar_cita = new Button(this);

            final Paciente paciente = matriz_datos.get(i);

            tv_cedula.setText(paciente.getCedula());
            tv_nombre.setText(paciente.getNombre());
            button_agregar_cita.setText("Agregar");

            button_agregar_cita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityCitasNuevo.this, ActivityCitasNuevo2.class);
                    intent.putExtra("id", paciente.getId());
                    startActivity(intent);
                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_agregar_cita.setAllCaps(false);
            }
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);

            fila.addView(tv_cedula);
            fila.addView(tv_nombre);
            fila.addView(button_agregar_cita);
            fila.setPadding(0,10,0,10);
            tablapos.addView(fila);

            
        }

    }


}

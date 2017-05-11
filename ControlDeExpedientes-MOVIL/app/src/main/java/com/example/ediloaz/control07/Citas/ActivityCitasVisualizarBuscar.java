package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 10/05/2017.
 */

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesEditar;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesVista;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.Pacientes.dbPacientesListado;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.Citas.ActivityCitasInicio;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosBuscar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosEditar;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosVista;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Medicos.dbListadoMedicos;
import com.example.ediloaz.control07.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityCitasVisualizarBuscar extends CommonCode {
    EditText edit_buscar;
    Button button_search;
    TableLayout tablapos;
    Spinner spinner_list;
    String[][] matriz_datos, matriz_datos_list;
    public ProgressBar progressBar;
    private TextView edit_fecha_cita, edit_hora_cita;
    private Button button_visualizar, button_seleccionar_cita;
    private int hora2, minutos,tiempo;
    private int id;
    private Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_visualizacion_citas);
        super.Listener();

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        button_visualizar = (Button)findViewById(R.id.button_VisualizarCita);
        button_visualizar.setOnClickListener(this);
        button_seleccionar_cita = (Button)findViewById(R.id.button_SeleccionarFechaCita);
        button_seleccionar_cita.setOnClickListener(this);

        edit_fecha_cita = (TextView)findViewById(R.id.text_Vista_Fecha);

        tablapos = (TableLayout)findViewById(R.id.table_Visualizar_list);
    }

    DatePickerDialog.OnDateSetListener listener_date = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            edit_fecha_cita.setText(year+ "/" + (monthOfYear+1) + "/" + dayOfMonth);

        }
    };


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_SeleccionarFechaCita:
                new DatePickerDialog(ActivityCitasVisualizarBuscar.this,listener_date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;


            case R.id.button_VisualizarCita:
                tablapos.removeAllViews();
                Log.i("_A_A_A_A_A_A_A_A_A_", "10");
                String texto_a_buscar = edit_fecha_cita.getText().toString();
                Log.i("_A_A_A_A_A_A_A_A_A_", "11"+texto_a_buscar);
                dbCitasVisualizacion db = new dbCitasVisualizacion(this, progressBar,texto_a_buscar);
                Log.i("_A_A_A_A_A_A_A_A_A_", "12");
                InputMethodManager imm = (InputMethodManager)getSystemService(this.getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edit_fecha_cita.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);
                db.execute();
                Log.i("_A_A_A_A_A_A_A_A_A_", "3");
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void llenarTabla(ArrayList<Cita> matriz_datos){
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after condition");
        for(int i=0; i<matriz_datos.size() ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set colours");
            TextView tv_fecha = new TextView(this);
            TextView tv_hora = new TextView(this);
            TextView tv_paciente = new TextView(this);



            tv_fecha.setText(matriz_datos.get(i).getFecha());
            tv_hora.setText(matriz_datos.get(i).getHora());
            tv_paciente.setText(matriz_datos.get(i).getPaciente().getNombre());

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after set text");

            tv_fecha.setTextSize(16);
            tv_hora.setTextSize(16);
            tv_paciente.setTextSize(16);
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "after define size");

            final Cita cita = matriz_datos.get(i);

            fila.addView(tv_fecha);
            fila.addView(tv_hora);
            fila.addView(tv_paciente);
            fila.setPadding(0,10,0,10);
            tablapos.addView(fila);

        }

    }

}

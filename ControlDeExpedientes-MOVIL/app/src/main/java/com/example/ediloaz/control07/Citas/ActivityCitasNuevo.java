package com.example.ediloaz.control07.Citas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ediloaz.control07.ActivityIngresar;
import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.ActivityMedicosInicio;
import com.example.ediloaz.control07.Pacientes.ActivityPacientesInicio;
import com.example.ediloaz.control07.Pacientes.dbPacientesListado;
import com.example.ediloaz.control07.R;

public class ActivityCitasNuevo extends CommonCode {
    Button button_buscar;
    TableLayout tablapos;
    EditText edit_buscar;
    String[][] matriz_datos, matriz_datos_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo);
        super.Listener();
        edit_buscar =  (EditText) findViewById(R.id.edit_CitasNuevo_Buscar);
        button_buscar = (Button)findViewById(R.id.button_CitasNuevo_Buscar);
        button_buscar.setOnClickListener(this);

        tablapos = (TableLayout)findViewById(R.id.table_CitasBuscar_list);
    }


    public void llenarTabla(){
        if(tablapos.getChildCount()>1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
        }
        for(int i=0; i<matriz_datos.length ; i++){
            TableRow fila = new TableRow(this);
            if(i%2 == 0) fila.setBackgroundColor(Color.parseColor("#FAFAFA"));
            else fila.setBackgroundColor(Color.parseColor("#D6D7D7"));
            TextView tv_cedula = new TextView(this);
            TextView tv_nombre = new TextView(this);
            Button button_agregar_cita = new Button(this);
            tv_cedula.setText(matriz_datos[i][0]);
            tv_nombre.setText(matriz_datos[i][1]);
            button_agregar_cita.setText("Agregar");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                button_agregar_cita.setAllCaps(false);
            }
            tv_cedula.setTextSize(16);
            tv_nombre.setTextSize(16);

            fila.addView(tv_cedula);
            fila.addView(tv_nombre);
            fila.addView(button_agregar_cita);
            fila.setPadding(0,10,0,10);

            if (matriz_datos[i][0] != null){
                tablapos.addView(fila);
            }
        }

    }
}

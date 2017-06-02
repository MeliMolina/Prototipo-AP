package com.example.ediloaz.control07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ediloaz.control07.Enfermedades.Enfermedad;
import com.example.ediloaz.control07.Estadisticas.dbContadorEnfermedades;
import com.example.ediloaz.control07.Estadisticas.dbEnfermedades;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView text;
    EditText edit;
    ImageView image;
    Button login_buton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_buton = (Button)findViewById(R.id.button_login);
        login_buton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                Intent intent = new Intent(getApplicationContext(),ActivityWelcome.class);
                startActivity(intent);
                break;
        }
    }

   /* public void llenarDatos() {

        dbEnfermedades db = new dbEnfermedades(-1);
        db.execute("");

        matriz_datos_list = db.GetMatriz();
        ArrayList<String> datos = new ArrayList<>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String string_temp;

        for (int i = 0; i < matriz_datos_list.size(); i++) {
            string_temp = matriz_datos_list.get(i).getDescripcion().toString();

            dbContadorEnfermedades dbc = new dbContadorEnfermedades(this,string_temp);
            dbc.execute("");

            cantidad_pacientes = dbc.GetCantidad_Pacientes();
            System.out.println(cantidad_pacientes);

            entries.add(new BarEntry(cantidad_pacientes,i));
            datos.add(string_temp);

        }
        for (int i = 0; i<matriz_datos_list.size(); i++){
            System.out.println(matriz_datos_list.get(i).getDescripcion().toString());
        }




    }*/

}

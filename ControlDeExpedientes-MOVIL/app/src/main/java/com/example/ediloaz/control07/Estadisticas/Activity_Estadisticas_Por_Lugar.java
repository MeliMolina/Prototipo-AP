package com.example.ediloaz.control07.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrador on 01/06/2017.
 */

public class Activity_Estadisticas_Por_Lugar extends CommonCode {
    private Button button_Sanjose,button_Alajuela, button_Cartago, button_Heredia, button_Limon, button_Puntarenas, button_Guanacaste;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_por_lugar);
        super.Listener();

        button_Sanjose = (Button)findViewById(R.id.button_sanjose);
        button_Sanjose.setOnClickListener(this);

        button_Alajuela = (Button)findViewById(R.id.button_alajuela);
        button_Alajuela.setOnClickListener(this);

        button_Cartago = (Button)findViewById(R.id.button_cartago);
        button_Cartago.setOnClickListener(this);

        button_Heredia = (Button)findViewById(R.id.button_heredia);
        button_Heredia.setOnClickListener(this);

        button_Limon = (Button)findViewById(R.id.button_limon);
        button_Limon.setOnClickListener(this);

        button_Puntarenas = (Button)findViewById(R.id.button_puntarenas);
        button_Puntarenas.setOnClickListener(this);

        button_Guanacaste = (Button)findViewById(R.id.button_guanacaste);
        button_Guanacaste.setOnClickListener(this);
    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_sanjose:
                Intent intent_sanjose = new Intent(this.getApplicationContext(), Activity_Enfermedades_SJ.class);
                startActivity(intent_sanjose);
                finish();

                break;

            case R.id.button_alajuela:
                Intent intent_alajuela = new Intent(this.getApplicationContext(), Activity_Enfermedades_Alajuela.class);
                startActivity(intent_alajuela);
                finish();

                break;

            case R.id.button_cartago:
                Intent intent_cartago = new Intent(this.getApplicationContext(), Activity_Enfermedades_Cartago.class);
                startActivity(intent_cartago);
                finish();

                break;

            case R.id.button_heredia:
                Intent intent_heredia = new Intent(this.getApplicationContext(), Activity_Enfermedades_Heredia.class);
                startActivity(intent_heredia);
                finish();

                break;

            case R.id.button_limon:
                Intent intent_limon = new Intent(this.getApplicationContext(), Activity_Enfermedades_Limon.class);
                startActivity(intent_limon);
                finish();

                break;

            case R.id.button_puntarenas:
                Intent intent_puntarenas = new Intent(this.getApplicationContext(), Activity_Enfermedades_Puntarenas.class);
                startActivity(intent_puntarenas);
                finish();

                break;

            case R.id.button_guanacaste:
                Intent intent_guanacaste = new Intent(this.getApplicationContext(), Activity_Enfermedades_Guanacaste.class);
                startActivity(intent_guanacaste);
                finish();

                break;
        }
    }
}

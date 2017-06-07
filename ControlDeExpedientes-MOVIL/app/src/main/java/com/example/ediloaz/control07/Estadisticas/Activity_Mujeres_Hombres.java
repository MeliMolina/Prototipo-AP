package com.example.ediloaz.control07.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrador on 01/06/2017.
 */

public class Activity_Mujeres_Hombres extends CommonCode{

    private Button button_Mujeres,button_Hombres;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_mujeres_hombres);
        super.Listener();

        button_Hombres = (Button)findViewById(R.id.button_hombres);
        button_Hombres.setOnClickListener(this);

        button_Mujeres = (Button)findViewById(R.id.button_mujeres);
        button_Mujeres.setOnClickListener(this);

    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_mujeres:
                Intent intent_mujeres = new Intent(this.getApplicationContext(), Activity_Estadisticas_Mujeres.class);
                startActivity(intent_mujeres);
                finish();

            case R.id.button_hombres:
                Intent intent_hombres = new Intent(this.getApplicationContext(), Activity_Estadisticas_Hombres.class);
                startActivity(intent_hombres);
                finish();

                break;

        }
    }



}
package com.example.ediloaz.control07.Estadisticas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrador on 02/06/2017.
 */

public class Activity_Estadisticas_Mujeres extends CommonCode {

    BarChart barchart_mujeres;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_mujeres);
        barchart_mujeres = (BarChart) findViewById(R.id.barchart_mujeres);

        ArrayList<BarEntry> entries_mujeres = new ArrayList<>();
        entries_mujeres.add(new BarEntry(4f, 0));
        entries_mujeres.add(new BarEntry(4f, 1));
        entries_mujeres.add(new BarEntry(4f, 2));
        entries_mujeres.add(new BarEntry(1f, 3));
        entries_mujeres.add(new BarEntry(2f, 4));
        entries_mujeres.add(new BarEntry(6f, 5));

        entries_mujeres.add(new BarEntry(3f, 6));
        entries_mujeres.add(new BarEntry(3f, 7));
        entries_mujeres.add(new BarEntry(1f, 8));
        entries_mujeres.add(new BarEntry(2f, 9));

    BarDataSet dataset_mujeres = new BarDataSet(entries_mujeres, "Enfermedades");

    ArrayList<String> labels_mujeres = new ArrayList<String>();
        labels_mujeres.add("Asma");
        labels_mujeres.add("Hepatitis A");
        labels_mujeres.add("Bronquitis");
        labels_mujeres.add("Agorafobia");
        labels_mujeres.add("Hepatitis B");
        labels_mujeres.add("Cáncer de Esófago");

        labels_mujeres.add("Diabetes ");
        labels_mujeres.add("Tumor Lóbulo Frontal Izquierdo");
        labels_mujeres.add("Alergia al Polvo");
        labels_mujeres.add("Gripe");

        dataset_mujeres.setColors(ColorTemplate.PASTEL_COLORS);

    BarData data_mujeres = new BarData(labels_mujeres, dataset_mujeres);
        barchart_mujeres.setData(data_mujeres);
        barchart_mujeres.setDescription("Enfermedades por mujeres");

}
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_Regresar:
                Intent intent_regresar = new Intent(this.getApplicationContext(), Activity_Mujeres_Hombres.class);
                startActivity(intent_regresar);
                finish();



                break;

        }
    }

}
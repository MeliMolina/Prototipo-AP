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

public class Activity_Estadisticas_Hombres extends CommonCode {

    BarChart barchart_mujeres;
    BarChart barchart_hombres;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_hombres);

        barchart_hombres = (BarChart) findViewById(R.id.barchart_hombres);

   ;

        ArrayList<BarEntry> entries_hombres = new ArrayList<>();
        entries_hombres.add(new BarEntry(5f, 0));
        entries_hombres.add(new BarEntry(4f, 1));
        entries_hombres.add(new BarEntry(6f, 2));
        entries_hombres.add(new BarEntry(1f, 3));
        entries_hombres.add(new BarEntry(8f, 4));
        entries_hombres.add(new BarEntry(6f, 5));

        entries_hombres.add(new BarEntry(3f, 6));
        entries_hombres.add(new BarEntry(4f, 7));
        entries_hombres.add(new BarEntry(1f, 8));
        entries_hombres.add(new BarEntry(10f, 9));

        BarDataSet dataset_hombres = new BarDataSet(entries_hombres, "Enfermedades");

        ArrayList<String> labels_hombres = new ArrayList<String>();
        labels_hombres.add("Asma");
        labels_hombres.add("Hepatitis A");
        labels_hombres.add("Bronquitis");
        labels_hombres.add("Agorafobia");
        labels_hombres.add("Hepatitis B");
        labels_hombres.add("Cáncer de Esófago");

        labels_hombres.add("Diabetes ");
        labels_hombres.add("Tumor Lóbulo Frontal Izquierdo");
        labels_hombres.add("Alergia al Polvo");
        labels_hombres.add("Gripe");

        dataset_hombres.setColors(ColorTemplate.PASTEL_COLORS);

        BarData data = new BarData(labels_hombres, dataset_hombres);
        barchart_hombres.setData(data);
        barchart_hombres.setDescription("Enfermedades por hombres");
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

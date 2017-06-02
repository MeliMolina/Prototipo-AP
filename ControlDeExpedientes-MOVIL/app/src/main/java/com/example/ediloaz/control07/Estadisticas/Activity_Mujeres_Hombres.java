package com.example.ediloaz.control07.Estadisticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class Activity_Mujeres_Hombres extends AppCompatActivity {

    HorizontalBarChart barchart;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enfermedades_mujeres_hombres);
        barchart = (HorizontalBarChart) findViewById(R.id.horizontalchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(4f, 2));
        entries.add(new BarEntry(1f, 3));
        entries.add(new BarEntry(2f, 4));
        entries.add(new BarEntry(6f, 5));
        entries.add(new BarEntry(3f, 6));
        entries.add(new BarEntry(3f, 7));
        entries.add(new BarEntry(1f, 8));
        entries.add(new BarEntry(2f, 9));

        BarDataSet dataset = new BarDataSet(entries, "Enfermedades");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Asma");
        labels.add("Hepatitis A");
        labels.add("Bronquitis");
        labels.add("Agorafobia");
        labels.add("Hepatitis B");
        labels.add("Cáncer de Esófago");
        labels.add("Diabetes ");
        labels.add("Tumor Lóbulo Frontal Izquierdo");
        labels.add("Alergia al Polvo");
        labels.add("Gripe");

        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(labels, dataset);
        barchart.setData(data);
        barchart.setDescription("Enfermedades por cantidad de pacientes");


        //setupPieChart();

    }

}
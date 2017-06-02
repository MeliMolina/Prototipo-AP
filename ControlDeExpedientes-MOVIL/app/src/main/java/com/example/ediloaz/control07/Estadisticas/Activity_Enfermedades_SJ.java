package com.example.ediloaz.control07.Estadisticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Administrador on 01/06/2017.
 */

public class Activity_Enfermedades_SJ extends AppCompatActivity {
    float rainfall [] = {98.8f,123.8f,161.6f,24.2f,52f,58.2f,35.4f,13.8f,78.4f,203.4f,240.2f,159.7f};
    String monthnames [] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};


    protected void onCreate(Bundle savedInstanceState) {

        PieChart pieChart ;
       ArrayList<Entry> entries ;
        ArrayList<String> PieEntryLabels ;
        PieDataSet pieDataSet ;
        PieData pieData ;

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_enfermedades_SJ);

            pieChart = (PieChart) findViewById(R.id.piegraph);

            entries = new ArrayList<>();

            PieEntryLabels = new ArrayList<String>();

        entries.add(new BarEntry(5f, 0));
        entries.add(new BarEntry(5f, 1));
        entries.add(new BarEntry(2f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(2f, 4));
        entries.add(new BarEntry(10f, 5));
        entries.add(new BarEntry(3f, 6));
        entries.add(new BarEntry(13f, 7));
        entries.add(new BarEntry(12f, 8));
        entries.add(new BarEntry(15f, 9));


        PieEntryLabels.add("Asma");
        PieEntryLabels.add("Hepatitis A");
        PieEntryLabels.add("Bronquitis");
        PieEntryLabels.add("Agorafobia");
        PieEntryLabels.add("Hepatitis B");
        PieEntryLabels.add("Cáncer de Esófago");
        PieEntryLabels.add("Diabetes ");
        PieEntryLabels.add("Tumor Lóbulo Frontal Izquierdo");
        PieEntryLabels.add("Alergia al Polvo");
        PieEntryLabels.add("Gripe");

        pieDataSet = new PieDataSet(entries, "Enfermedades en San José");

            pieData = new PieData(PieEntryLabels, pieDataSet);

            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            pieChart.setData(pieData);

            pieChart.animateY(3000);

        }

}






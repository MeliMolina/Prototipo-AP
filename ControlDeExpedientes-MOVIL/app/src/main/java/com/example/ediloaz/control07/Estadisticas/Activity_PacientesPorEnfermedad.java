package com.example.ediloaz.control07.Estadisticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntrySet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Activity_PacientesPorEnfermedad extends AppCompatActivity {
    BarChart barchart;
    float rainfall [] = {98.8f,123.8f,161.6f,24.2f,52f,58.2f,35.4f,13.8f,78.4f,203.4f,240.2f,159.7f};
    String monthnames [] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pacientes_por_enfermedad);
        barchart = (BarChart) findViewById(R.id.bargraph);

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
    /*private void setupPieChart(){
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i<rainfall.length; i++){
            pieEntries.add(new PieEntry(rainfall[i],monthnames[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries,"Rainfall");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.invalidate();
    }*/
}
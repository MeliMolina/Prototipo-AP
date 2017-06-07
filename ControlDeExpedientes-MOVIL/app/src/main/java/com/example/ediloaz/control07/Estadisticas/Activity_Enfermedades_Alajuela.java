package com.example.ediloaz.control07.Estadisticas;

/**
 * Created by Administrador on 01/06/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

public class Activity_Enfermedades_Alajuela extends CommonCode {
    Button button_regresar;

    protected void onCreate(Bundle savedInstanceState) {

        PieChart pieChart ;
        ArrayList<Entry> entries ;
        ArrayList<String> PieEntryLabels ;
        PieDataSet pieDataSet ;
        PieData pieData ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_alajuela);

        button_regresar = (Button)findViewById(R.id.button_Regresar);
        button_regresar.setOnClickListener(this);

        pieChart = (PieChart) findViewById(R.id.piegraph);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        entries.add(new BarEntry(5f, 0));
        entries.add(new BarEntry(5f, 1));
        entries.add(new BarEntry(2f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(2f, 4));
        entries.add(new BarEntry(10f, 5));
        /*
        entries.add(new BarEntry(3f, 6));
        entries.add(new BarEntry(13f, 7));
        entries.add(new BarEntry(12f, 8));
        entries.add(new BarEntry(15f, 9));*/


        PieEntryLabels.add("Asma");
        PieEntryLabels.add("Hepatitis A");
        PieEntryLabels.add("Bronquitis");
        PieEntryLabels.add("Agorafobia");
        PieEntryLabels.add("Hepatitis B");
        PieEntryLabels.add("Cáncer de Esófago");

        /*PieEntryLabels.add("Diabetes ");
        PieEntryLabels.add("Tumor Lóbulo Frontal Izquierdo");
        PieEntryLabels.add("Alergia al Polvo");
        PieEntryLabels.add("Gripe");*/

        pieDataSet = new PieDataSet(entries, "Enfermedades en Alajuela");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_Regresar:
                Intent intent_regresar = new Intent(this.getApplicationContext(), Activity_Estadisticas_Por_Lugar.class);
                startActivity(intent_regresar);
                finish();



                break;

        }
    }
}

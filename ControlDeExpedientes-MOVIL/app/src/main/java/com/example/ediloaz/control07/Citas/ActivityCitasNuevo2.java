package com.example.ediloaz.control07.Citas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.R;

public class ActivityCitasNuevo2 extends CommonCode {
    Button button_agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_nuevo2);
        super.Listener();
        button_agregar = (Button)findViewById(R.id.button_CitasNuevo2_Agregar);
        button_agregar.setOnClickListener(this);
    }
}

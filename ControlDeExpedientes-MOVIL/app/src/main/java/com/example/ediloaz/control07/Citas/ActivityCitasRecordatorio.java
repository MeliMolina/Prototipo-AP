package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 24/04/2017.
 */

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.content.Intent;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Pacientes.dbEnlacesPaciente;
import com.example.ediloaz.control07.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ediloaz.control07.CommonCode;
import com.example.ediloaz.control07.Enfermedades.dbEnfermedadesEditar;
import com.example.ediloaz.control07.R;

public class ActivityCitasRecordatorio extends CommonCode {
    private EditText  edit_mensaje_email, txtCorreo;
    private Button button_enviar;
    private ProgressBar progressBar;
    private String fecha_cita, hora_cita, mensaje, medico, paciente;
    private int id;
    public boolean correctFinished;
    private ActivityCitasRecordatorio activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_recordatorio);
        super.Listener();


        button_enviar = (Button)findViewById(R.id.button_EnviarRecordatorio);
        button_enviar.setOnClickListener(this);


        edit_mensaje_email =  (EditText) findViewById(R.id.edit_mensajeEmail);


        fecha_cita = getIntent().getStringExtra("fecha");
        hora_cita = getIntent().getStringExtra("hora");
        medico = getIntent().getStringExtra("medico");
        paciente = getIntent().getStringExtra("paciente");
        id = getIntent().getIntExtra("id", 0);



        edit_mensaje_email.setText("Buenas estimado(a)" + paciente + ",el siguiente correo es para recordarle la fecha y hora de su cita:\n"+ "Fecha: " + fecha_cita + "\n" + "Hora: "+ hora_cita + "\n" + "Médico: " + medico +  "\n "+ "Saludos\n\n"+ "__________________________\n"+"Expediente Médico");


    }
    @Override
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()) {

            case R.id.button_EnviarRecordatorio:
                llenarCorreoPaciente(id);

                break;
        }
    }

    public void llenarCorreoPaciente(int pId){
        try {
            dbEnlacesPaciente db_correo = new dbEnlacesPaciente(pId,"correo","select","","");
            db_correo.execute("").get();
            String correo = db_correo.GetResultado();
            mensaje = edit_mensaje_email.getText().toString();
            String[] to = {correo};
            enviar(to,mensaje);

        } catch (Exception e) {
            Intent intent_Ingresar = new Intent(this.getApplicationContext(), ActivityCitasVista.class);
            startActivity(intent_Ingresar);
            finish();
        }
    }

    private void enviar(String[] to,String mensaje) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Selecciona la aplicación..."));

    }

}

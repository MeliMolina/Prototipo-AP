package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 05/06/2017.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.IntentService;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesBuscar;
import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesInicio;
import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Pacientes.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbNombrePacientes extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String email,nombre;
    private int fila;
    public ArrayList<Paciente> matriz_datos;

    private ProgressBar progressBar;
    private final TimerTask activity;

    public dbNombrePacientes(TimerTask pactivity, String pEmail){
        matriz_datos = new ArrayList<Paciente>();
        email = pEmail;
        activity = pactivity;

    }

   /* public Date sumarDiasFecha(Date fecha){

        Date date = new Date();

       // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //Calendar calendar = Calendar.getInstance();

        calendar.setTime(date); // Configuramos la fecha que se recibe

        calendar.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

        Calendar fechaActual = Calendar.getInstance();
        String cadenaFecha;
                format("%04d-%02d-%02d-%02d", fechaActual.get(Calendar.YEAR), fechaActual.get(Calendar.MONTH)+1, fechaActual.get(Calendar.DAY_OF_MONTH)+1);
        System.out.println(cadenaFecha);

    }*/

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

       // progressBar.setVisibility(View.GONE);

        //((ActivityCitasInicio) activity).llenarTabla(matriz_datos);
        ((Temporizador) activity).run();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressBar.setVisibility(View.VISIBLE);
        //progressBar.setIndeterminate(true);
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            Date date = new Date();

            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //fecha_cita = dateFormat.format(date);


            PreparedStatement stmt;
            stmt = conn.prepareStatement("SELECT DISTINCT P.nombre FROM pacientes P INNER JOIN emails E ON E.paciente_id = P.id WHERE E.email = '" + email + "';");


            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                nombre = rs.getString(1);

                Paciente nombre_paciente = new Paciente(nombre,"","");

                matriz_datos.add(nombre_paciente);

            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public ArrayList<Paciente> GetMatriz(){
        return matriz_datos;
    }


}

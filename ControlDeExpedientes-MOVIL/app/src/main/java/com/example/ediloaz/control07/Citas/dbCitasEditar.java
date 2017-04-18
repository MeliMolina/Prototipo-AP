package com.example.ediloaz.control07.Citas;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesEditar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrador on 16/04/2017.
 */

public class dbCitasEditar extends AsyncTask<String, Integer, String> {
    private String fecha, hora,messageFinished;
    private int id;
    public boolean correctFinished;
    private ActivityCitasEditar activity;
    private ProgressBar progressBar;
    private Connection conn;

    public dbCitasEditar(ActivityCitasEditar pActivity, ProgressBar pProgressBar, String pFecha, String pHora, int pId){
        fecha = pFecha;
        hora = pHora;
        activity = pActivity;
        progressBar = pProgressBar;
        id = pId;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Cita actualizada exitosamente.", Toast.LENGTH_LONG).show();
            activity.openCitas(activity.getApplicationContext());
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "5");
        }else{
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "6");
            Toast.makeText(activity.getApplicationContext(),"Error en la actualización de la cita", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            correctFinished = false;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Conexión a la BD exitosa");

            PreparedStatement stmt;

                stmt = conn.prepareStatement("UPDATE cita SET fecha = '" + fecha + "', hora = '" + hora + "', " +
                        "created_at = NOW(), updated_at = NOW() WHERE id = " + id + ";");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");

            correctFinished = true;

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }



}

package com.example.ediloaz.control07.Observaciones.Pacientes;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Observaciones.Pacientes.ActivityPacienteObservacionEditar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Dell on 14/1/2017.
 */

public class dbPacienteObservacionesEditar extends AsyncTask<String, Integer, String> {
    private Connection conn;

    private String descripcion;
    private int id;

    public boolean correctFinished;

    private ActivityPacienteObservacionEditar activity;
    private ProgressBar progressBar;

    public dbPacienteObservacionesEditar(ActivityPacienteObservacionEditar pActivity, ProgressBar pProgressBar, String pDescripcion, int pId){
        this.descripcion = pDescripcion;
        this.activity = pActivity;
        this.progressBar = pProgressBar;
        this.id = pId;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);

        if (correctFinished == true){
            this.activity.openObservacion(this.activity.getApplicationContext());
            Toast.makeText(activity.getApplicationContext(),"Observación actualizada exitosamente.", Toast.LENGTH_LONG).show();
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

//            Verificar si ya el codigo existe
            PreparedStatement stmt = conn.prepareStatement("UPDATE observacions SET descripcion = '" + descripcion + "', " +
                        "updated_at = NOW(), updated_at = NOW() WHERE id = " + id + ";");
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

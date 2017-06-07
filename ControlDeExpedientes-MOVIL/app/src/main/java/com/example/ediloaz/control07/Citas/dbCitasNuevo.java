package com.example.ediloaz.control07.Citas;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ediloaz.control07.Enfermedades.ActivityEnfermedadesEditar;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Created by Administrador on 16/04/2017.
 */

public class dbCitasNuevo extends AsyncTask<String, Integer, String> {
    private String fecha, hora,messageFinished;
    private int id_medico, id;
    public boolean correctFinished;
    private ActivityCitasNuevo2 activity;
    private ProgressBar progressBar;
    private Connection conn;
    private SessionManager session;
    private HashMap<String, String> medico;

    public dbCitasNuevo(ActivityCitasNuevo2 pActivity, ProgressBar pProgressBar, String pFecha, String pHora, int pId){
        fecha = pFecha;
        hora = pHora;
        activity = pActivity;
        progressBar = pProgressBar;
        id = pId;
        session = new SessionManager(activity);
        medico = getSession().getUserDetails();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.GONE);


        if (correctFinished == true){
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "4");
            Toast.makeText(activity.getApplicationContext(),"Cita agregada exitosamente.", Toast.LENGTH_LONG).show();
            this.activity.openCitas(this.activity.getApplicationContext());
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "5");
        }else{
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "6");
            Toast.makeText(activity.getApplicationContext(),"No se pudo agregar la cita", Toast.LENGTH_LONG).show();
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
            stmt = conn.prepareStatement("select id from pacientes order by id desc limit 1;");
            ResultSet rs = stmt.executeQuery();
            rs = stmt.executeQuery();

            if(rs.next()) {

                id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
                stmt = conn.prepareStatement("INSERT INTO cita (medico_id, created_at, updated_at, " + "paciente_id, fecha," + " hora) VALUES ('" + id_medico + "',NOW(), NOW(), '" + id + "', '" + fecha+ "','" + hora + "');");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.executeUpdate();

            }

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
            correctFinished = true;
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }


    public SessionManager getSession() {
        return session;
    }



}

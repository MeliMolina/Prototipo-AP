package com.example.ediloaz.control07.Citas;

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
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbCitasPaciente extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String descripcion, mensaje, nombre,apellido1,apellido2;
    private int fila;
    public ArrayList<Cita> matriz_datos;
    private int id_medico;

    private ProgressBar progressBar;
    private final Activity activity;
    private SessionManager session;
    HashMap<String, String> medico;


    public dbCitasPaciente(Activity pActivity, ProgressBar pProgressBar, String pnombre, String papellido1, String papellido2){
        matriz_datos = new ArrayList<Cita>();
        activity = pActivity;
        progressBar = pProgressBar;
        nombre = pnombre;
        apellido1 = papellido1;
        apellido2 = papellido2;


        session = new SessionManager(activity);
        medico = getSession().getUserDetails();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        ((ActivityBuscarCitasVista) activity).llenarTabla(matriz_datos);

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
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
            Log.i("Medico actual ","abcd"+id_medico);

            PreparedStatement stmt;

            stmt = conn.prepareStatement("SELECT C.id, C.fecha, C.hora FROM cita C INNER JOIN pacientes P ON C.paciente_id = P.id " +
            "WHERE P.nombre = '" + nombre + "' AND P.apellido1 = '" + apellido1 + "' AND P.apellido2 = '" + apellido2 +  "';");
            ResultSet rs = stmt.executeQuery();


            String fecha, hora;

            int idCita;

            while(rs.next()) {
                idCita = rs.getInt(1);
                fecha = rs.getString(2);
                hora = rs.getString(3);
                mensaje = "Fecha de la cita: " + fecha + "Hora de la cita: " + hora;
                Cita cita = new Cita(idCita,fecha,hora);

                matriz_datos.add(cita);
                Log.v("Aviso: ",mensaje);
            }
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

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

public class dbCitasVisualizacion extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String descripcion, mensaje, fecha;
    private int fila;
    public ArrayList<Cita> matriz_datos;
    private int id_medico;

    private ProgressBar progressBar;
    private final Activity activity;
    private SessionManager session;
    HashMap<String, String> medico;


    public dbCitasVisualizacion(Activity pActivity, ProgressBar pProgressBar,String pFecha){
        matriz_datos = new ArrayList<Cita>();
        activity = pActivity;
        progressBar = pProgressBar;
        fecha = pFecha;


        session = new SessionManager(activity);
        medico = getSession().getUserDetails();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        ((ActivityCitasVisualizarBuscar) activity).llenarTabla(matriz_datos);

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
            stmt = conn.prepareStatement("SELECT C.fecha, C.hora, P.nombre, P.apellido1, P.apellido2 FROM cita C INNER JOIN medicos ON C.medico_id= "+ id_medico + " INNER JOIN pacientes P ON C.paciente_id=P.id "
            + " WHERE (C.fecha = " + fecha + ";");


            ResultSet rs = stmt.executeQuery();

            String fecha, hora;
            String medicoNombre, medicoApellido1, medicoApellido2;
            String pacienteNombre, pacienteApellido1, pacienteApellido2;

            int idCita;

            while(rs.next()) {
                fecha = rs.getString(1);
                hora = rs.getString(2);
                idCita = rs.getInt(3);

                pacienteNombre = rs.getString(7);
                pacienteApellido1 = rs.getString(8);
                pacienteApellido2 = rs.getString(9);


                Paciente paciente = new Paciente(pacienteNombre,pacienteApellido1,pacienteApellido2);

                Cita cita = new Cita(idCita,fecha,hora,paciente);

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

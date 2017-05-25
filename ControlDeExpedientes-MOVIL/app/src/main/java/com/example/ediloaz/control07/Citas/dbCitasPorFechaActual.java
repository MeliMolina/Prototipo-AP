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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbCitasPorFechaActual extends AsyncTask<String, Integer, String> {

    private Connection conn;
    private String descripcion, mensaje, nombre,apellido1, apellido2,fecha_actual;
    private int fila;
    public ArrayList<Cita> matriz_datos;

    private ProgressBar progressBar;
    private final Activity activity;


    public dbCitasPorFechaActual(Activity pActivity, ProgressBar pProgressBar,String pFechaActual){
        matriz_datos = new ArrayList<Cita>();
        activity = pActivity;
        progressBar = pProgressBar;
        fecha_actual = pFechaActual;
    }
    public dbCitasPorFechaActual(Activity pActivity, ProgressBar pProgressBar,String pNombre, String pApellido1, String pApellido2){
        matriz_datos = new ArrayList<Cita>();
        activity = pActivity;
        progressBar = pProgressBar;
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressBar.setVisibility(View.GONE);

        ((ActivityCitasInicio) activity).llenarTabla(matriz_datos);

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

            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            PreparedStatement stmt;
            stmt = conn.prepareStatement("SELECT C.fecha, C.hora, C.id, M.nombre, M.apellido1, M.apellido2, P.nombre, P.apellido1, P.apellido2 FROM cita C INNER JOIN medicos M ON C.medico_id=M.id INNER JOIN pacientes P ON C.paciente_id=P.id " +
            "WHERE C.fecha = " + "'" +  dateFormat.format(date) + "'" + ";");


            ResultSet rs = stmt.executeQuery();

            String fecha, hora;
            String medicoNombre, medicoApellido1, medicoApellido2;
            String pacienteNombre, pacienteApellido1, pacienteApellido2;

            int idCita;

            while(rs.next()) {
                fecha = rs.getString(1);
                hora = rs.getString(2);
                idCita = rs.getInt(3);

                medicoNombre = rs.getString(4);
                medicoApellido1 = rs.getString(5);
                medicoApellido2 = rs.getString(6);

                pacienteNombre = rs.getString(7);
                pacienteApellido1 = rs.getString(8);
                pacienteApellido2 = rs.getString(9);

                mensaje = "La cita con " + medicoNombre + " con paciente" + pacienteNombre;

                Medico medico = new Medico(medicoNombre,medicoApellido1,medicoApellido2);
                Paciente paciente = new Paciente(pacienteNombre,pacienteApellido1,pacienteApellido2);

                Cita cita = new Cita(idCita,fecha,hora,medico,paciente);

                matriz_datos.add(cita);
                Log.v("Aviso: ",mensaje);
            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }


}

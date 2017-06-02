package com.example.ediloaz.control07.Estadisticas;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Enfermedades.Enfermedad;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrador on 29/05/2017.
 */

public class dbContadorEnfermedades extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje,enfermedad, mensaje_enfermedad,mensaje_medico;
    int fila;

    int id_paciente, id_medico,cantidad_pacientes;

    private SessionManager session;
    HashMap<String, String> medico;
    private final Activity activity;

    public dbContadorEnfermedades(Activity pActivity,String pEnfermedad){

        activity = pActivity;

        enfermedad = pEnfermedad;

        session = new SessionManager(activity);

        medico = getSession().getUserDetails();
    }

    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt;

            id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Id medico");

            mensaje_medico = "El id del medico es " + id_medico;
            Log.d("_A_A_A_A_A_A_A_A_A_A_A_", mensaje_medico);

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Enfermedad recibida");

            mensaje_enfermedad = "La enfermedad es " + enfermedad;
            Log.d("_A_A_A_A_A_A_A_A_A_A_A_", mensaje_enfermedad);

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Haciendo contador de enfermedades");

            stmt = conn.prepareStatement("SELECT COUNT( EP.paciente_id )cantidad FROM enfermedads_pacientes EP INNER JOIN enfermedads E ON E.descripcion = '"+ enfermedad + "' AND E.id = EP.enfermedad_id" +
                    " INNER JOIN medicos_pacientes MP ON MP.paciente_id = EP.paciente_id AND MP.medico_id = " + id_medico + ";");

            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");

            rs.next();
            int cantidad = Integer.parseInt(rs.getString(1));
                mensaje = "La cantidad de pacientes  es" + cantidad;
                cantidad_pacientes = cantidad;
            Log.d("_A_A_A_A_A_A_A_A_A_A_A_", mensaje);
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public  int GetCantidad_Pacientes(){
        return cantidad_pacientes;
    }

    public SessionManager getSession() {
        return session;
    }

}

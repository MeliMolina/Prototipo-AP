package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 19/05/2017.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Pacientes.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrador on 19/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Pacientes.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrador on 19/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ediloaz on 11/01/2017.
 */

public class dbApellido2PacientesSpinner extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje;
    int fila;
    public ArrayList<Paciente> matriz_datos;
    int id_paciente,id_medico;

    private SessionManager session;
    HashMap<String, String> medico;

    private final Activity activity;


    public dbApellido2PacientesSpinner(Activity pActivity,int pId){
        id_paciente = pId;
        matriz_datos = new ArrayList<Paciente>();

        activity = pActivity;

        session = new SessionManager(activity);
        medico = getSession().getUserDetails();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt;
            id_medico = Integer.parseInt(medico.get(SessionManager.KEY_ID));
            stmt = conn.prepareStatement("SELECT DISTINCT apellido2 FROM pacientes P INNER JOIN medicos_pacientes MP ON MP.medico_id = " + id_medico + " AND MP.paciente_id = P.id;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String apellido2 = rs.getString("apellido2");
                mensaje = "El segudno apellido es " + apellido2;
                Paciente paciente = new Paciente("","",apellido2);
                matriz_datos.add(paciente);
                Log.v("Aviso: ",mensaje);
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

    public SessionManager getSession() {
        return session;
    }


}
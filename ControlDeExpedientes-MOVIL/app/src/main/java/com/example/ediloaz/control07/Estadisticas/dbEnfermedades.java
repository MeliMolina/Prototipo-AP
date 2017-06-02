package com.example.ediloaz.control07.Estadisticas;

/**
 * Created by Administrador on 29/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.Enfermedades.Enfermedad;
import com.example.ediloaz.control07.Pacientes.Paciente;
import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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

public class dbEnfermedades extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje;
    int fila;
    public ArrayList<Enfermedad> matriz_datos;
    int id_paciente, id_medico;

    private SessionManager session;
    HashMap<String, String> medico;

    public dbEnfermedades(int pId){
        id_paciente = pId;
        matriz_datos = new ArrayList<Enfermedad>();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            PreparedStatement stmt;
            stmt = conn.prepareStatement("SELECT descripcion FROM enfermedads;");

            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta de enfermedades realizadas (dbEnfermedades)");

            while(rs.next()) {
                String enfermedad = rs.getString("descripcion");
                mensaje = "La enfermedad es" + enfermedad;
                Enfermedad enfermedades = new Enfermedad("",enfermedad);
                matriz_datos.add(enfermedades);
                //Log.d("_A_A_A_A_A_A_A_A_A_A_A_", mensaje);
            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public ArrayList<Enfermedad> GetMatriz(){
        return matriz_datos;
    }


    public SessionManager getSession() {
        return session;
    }


}
package com.example.ediloaz.control07.Pacientes;

/**
 * Created by Administrador on 28/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrador on 28/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.example.ediloaz.control07.SessionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrador on 28/05/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

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

public class dbSpinnerDistritos extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje, nombre_canton;
    int fila;
    public ArrayList<Countries> matriz_datos;
    int id_paciente, id_medico;

    private SessionManager session;
    HashMap<String, String> medico;

    public dbSpinnerDistritos(String Canton){
        nombre_canton = Canton;
        matriz_datos = new ArrayList<Countries>();
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
            stmt = conn.prepareStatement("SELECT DISTINCT district FROM countries WHERE canton = '" + nombre_canton + "';");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String distrito = rs.getString("district");
                mensaje = "El distrito es " + distrito;
                Countries distritos = new Countries("","",distrito);
                matriz_datos.add(distritos);
                Log.v("Aviso: ",mensaje);
            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public ArrayList<Countries> GetMatriz(){
        return matriz_datos;
    }


    public SessionManager getSession() {
        return session;
    }


}
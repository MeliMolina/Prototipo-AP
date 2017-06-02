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

public class dbSpinnerCantones extends AsyncTask<String, Integer, String> {
    Connection conn;
    private String mensaje, nombre_provincia;
    int fila;
    public ArrayList<Countries> matriz_datos;
    int id_paciente, id_medico;

    private SessionManager session;
    HashMap<String, String> medico;

    public dbSpinnerCantones(String Provincia){
        nombre_provincia = Provincia;
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
            stmt = conn.prepareStatement("SELECT DISTINCT canton FROM countries WHERE province = '" + nombre_provincia + "';");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String canton = rs.getString("canton");
                mensaje = "El cantón es " + canton;
                Countries cantones = new Countries("",canton,"");
                matriz_datos.add(cantones);
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
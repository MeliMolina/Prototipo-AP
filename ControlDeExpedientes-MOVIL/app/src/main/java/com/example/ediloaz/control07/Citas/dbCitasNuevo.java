package com.example.ediloaz.control07.Citas;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ediloaz on 13/01/2017.
 */

public class dbCitasNuevo implements Runnable {
    Connection conn;
    private String nombre, apellido1, apellido2, cedula, codigo, email, password, messageFinished;
    private String descripcion, mensaje, type;
    private String pass;
    private String message;
    private boolean isUser;
    private int fila;
    private int count;
    public String[][] matriz_datos = new String[25][2];
    public boolean correctFinished;

    public dbCitasNuevo(String pNombre, String pApellido1, String pApellido2, String pCedula, String pCodigo, String pEmail, String pPassword){
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        cedula = pCedula;
        codigo = pCodigo;
        email = pEmail;
        password = pPassword;
    }

    public String getMessage(){
        return message;
    }

    public boolean isUser(){
        return isUser;
    }

    @Override
    public void run() {
        try {
            correctFinished = false;

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Conexión a la BD exitosa");


//            Verificar si ya el codigo existe
            PreparedStatement stmt = conn.prepareStatement("SELECT count(cedula) FROM medicos  WHERE cedula = '" + cedula + "' OR codigo = '" + codigo + "';");
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo"+codigo);
            ResultSet rs = stmt.executeQuery();
            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo"+count);
            rs.next();
            count = Integer.parseInt(rs.getString(1));

            Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "SELECT COUNT(*) FROM MEDICOS  WHERE codigo"+count);

            if (count>=1) {
                correctFinished = false;
            }else if(nombre == "" || apellido1 == "" || apellido2 == "" || cedula == "" || codigo== "" || email== "" || password == "" ){
                correctFinished = false;
                messageFinished = "Todos los campos son obligatorios";
            }else{
                stmt = conn.prepareStatement("INSERT INTO medicos(email, sign_in_count, created_at, updated_at, codigo, nombre, apellido1, apellido2, cedula, nacionalidad, passcode, activo) VALUES ('" + email + "', 99, NOW(), NOW(), '" + codigo + "', '" + nombre+ "','" + apellido1 + "','" + apellido2 + "','" + cedula + "','Costarricense','" + password + "',0);");
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta creada");
                stmt.execute();
                Log.w("_A_A_A_A_A_A_A_A_A_A_A_", "Consulta realizada");
                correctFinished = true;
            }

            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }

    }

}
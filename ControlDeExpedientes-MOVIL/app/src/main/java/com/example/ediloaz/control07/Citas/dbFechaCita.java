package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 05/06/2017.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.Pacientes.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ediloaz.control07.Pacientes.Paciente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Administrador on 05/06/2017.
 */

public class dbFechaCita extends AsyncTask<String, Integer, String> {
    private Connection conn;
    private String email,nombre,apellido1,apellido2,fecha;
    private int fila,dias,id_cita;
    public ArrayList<Cita> matriz_datos;

    private ProgressBar progressBar;
    //private final Activity activity;

    private final TimerTask activity;

    public dbFechaCita(TimerTask pactivity,String pNombre, String pApellido1, String pApellido2){
        matriz_datos = new ArrayList<Cita>();
        nombre = pNombre;
        apellido1 = pApellido1;
        apellido2 = pApellido2;
        activity = pactivity;


    }

   /* public Date sumarDiasFecha(Date fecha){

        Date date = new Date();

       // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //Calendar calendar = Calendar.getInstance();

        calendar.setTime(date); // Configuramos la fecha que se recibe

        calendar.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

        Calendar fechaActual = Calendar.getInstance();
        String cadenaFecha;
                format("%04d-%02d-%02d-%02d", fechaActual.get(Calendar.YEAR), fechaActual.get(Calendar.MONTH)+1, fechaActual.get(Calendar.DAY_OF_MONTH)+1);
        System.out.println(cadenaFecha);

    }*/

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //progressBar.setVisibility(View.GONE);

        //((ActivityCitasInicio) activity).llenarTabla(matriz_datos);

        Log.v("dbEmails","OnPostExecute");

        ((Temporizador) activity).run();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // progressBar.setVisibility(View.VISIBLE);
        //progressBar.setIndeterminate(true);
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.freehostia.com:3306/kenqui_expctlr", "kenqui_expctlr", "adminexpctlr");
            Log.w("LoginActivity", "Conexión");

            Date date = new Date();

            //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            //fecha_cita = dateFormat.format(date);

            Calendar fechaActual = Calendar.getInstance();
            String cadenaFecha;
            cadenaFecha = fechaActual.get(Calendar.YEAR) +"-" + fechaActual.get(Calendar.MONTH)+1 + "-" + fechaActual.get(Calendar.DAY_OF_MONTH)+dias;


            PreparedStatement stmt;
            stmt = conn.prepareStatement("SELECT DISTINCT C.id, C.fecha FROM cita C INNER JOIN pacientes P ON C.paciente_id = P.id WHERE P.nombre = '" + nombre + "' AND P.apellido1 = '"+ apellido1 +"' AND P.apellido2 = '"+ apellido2 + "' AND C.fecha = '2017-06-09';");


            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                id_cita = rs.getInt(1);
                fecha = rs.getString(2);

                Cita fecha_cita = new Cita(id_cita,fecha,"");

                matriz_datos.add(fecha_cita);

            }
            conn.close();

        }catch (Exception e){
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
        return "";
    }

    public ArrayList<Cita> GetMatriz(){
        return matriz_datos;
    }

}

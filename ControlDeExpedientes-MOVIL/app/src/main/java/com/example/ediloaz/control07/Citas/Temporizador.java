package com.example.ediloaz.control07.Citas;
import android.util.Log;
import android.widget.Toast;

import com.example.ediloaz.control07.Pacientes.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrador on 04/06/2017.
 */

public class Temporizador extends TimerTask {

    private final Properties properties = new Properties();
    private Activity_Citas_Recordatorio activity;
    private boolean correctFinished;
    private int dia;
    private ArrayList<Email> matriz_datos_emails;
    private ArrayList<Paciente> matriz_datos_nombre;
    private ArrayList<Paciente> matriz_datos_apellido1;
    private ArrayList<Paciente> matriz_datos_apellido2;
    private ArrayList<Cita> matriz_datos_fecha;
    private ArrayList<Cita> matriz_datos_hora;

    private List<String> emails;
    private List<String> nombre;
    private List<String>apellido1;
    private List<String> apellido2;
    private List<String> fecha;
    private List<String> hora;
    private String paciente;




    private Session session;

    private void init() {

        // Nombre del host de correo, es smtp.gmail.com
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");

// TLS si está disponible
        properties.setProperty("mail.smtp.starttls.enable", "true");

// Puerto de gmail para envio de correos
        properties.setProperty("mail.smtp.port", "587");

// Nombre del usuario
        properties.setProperty("mail.smtp.user", "expedientemedicotec@gmail.com");

// Si requiere o no usuario y password para conectarse.
        properties.setProperty("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
    }


    //@Override
    protected void evaluar_resultado(boolean result) {

        if (correctFinished == true){
            Toast.makeText(activity.getApplicationContext(),"Se ha configurado correctamente el recordatorio de citas", Toast.LENGTH_LONG).show();
            this.activity.openCitas(this.activity.getApplicationContext());
        }else{

            Toast.makeText(activity.getApplicationContext(),"No se pudo configurar el recordatorio correctamente", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void run() {
        init();
        try {
            //correctFinished = false;

            Log.v("Temporizador","Entrando a enviar email");

            emails = ObtenerEmails();

            MimeMessage message = new MimeMessage(session);
            // Quien envia el correo
            message.setFrom(new InternetAddress("expedientemedicotec@gmail.com"));

            for(int i = 0; i< emails.size(); i++) {

                nombre = ObtenerNombre(emails.get(i));
                apellido1 = ObtenerApellido1(emails.get(i));
                apellido2 = ObtenerApellido2(emails.get(i));

                //fecha = ObtenerFechaCita(nombre.get(i),apellido1.get(i),apellido2.get(i));
                hora = ObtenerHoraCita(nombre.get(i),apellido1.get(i),apellido2.get(i));
                paciente = nombre.get(i) + " " + apellido1.get(i) + " " + apellido2.get(i);
                // A quien va dirigido
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emails.get(i)));
                message.setSubject("Recordatorio de cita");
                message.setText("Buenas estimado(a)" + paciente + "\n"+"El presente correo es para recordarle que su cita es el  2017-06-10  a las "+ hora.get(i) +"\n Saludos\n" + "___________________________\n" + "Expediente Médico Virtual");
                Transport t = session.getTransport("smtp");
                t.connect("expedientemedicotec@gmail.com", "adminexpctlr");
                t.sendMessage(message, message.getAllRecipients());
                //correctFinished = true;
                t.close();

            }
            Log.v("Temporizador","Termine el email");
           // evaluar_resultado(correctFinished);



        } catch (Exception e) {
            Log.w("LoginActivity","ERROR: Conexión---" +e.getMessage());
        }
    }

    public List<String>  ObtenerEmails(){

            dbEmails db = new dbEmails(this,dia);
            db.execute("");

            matriz_datos_emails = db.GetMatriz();
            List<String> list = new ArrayList<String>();
            String string_temp;
            for(int i=0; i<matriz_datos_emails.size(); i++){
                string_temp = matriz_datos_emails.get(i).GetEmail().toString();
                list.add(string_temp);
            }
            return list;
    }


    public List<String>  ObtenerNombre(String pEmail){
        dbNombrePacientes db = new dbNombrePacientes(this,pEmail);
        db.execute("");

        matriz_datos_nombre = db.GetMatriz();
        List<String> list = new ArrayList<String>();
        String string_temp;
        for(int i=0; i<matriz_datos_nombre.size(); i++){
            string_temp = matriz_datos_nombre.get(i).getNombre().toString();
            list.add(string_temp);
        }
        return list;
    }


    public List<String> ObtenerApellido1(String pEmail){
        dbApellido1Pacientes db = new dbApellido1Pacientes(this,pEmail);
        db.execute("");

        matriz_datos_apellido1 = db.GetMatriz();
        List<String> list = new ArrayList<String>();
        String string_temp;
        for(int i=0; i<matriz_datos_apellido1.size(); i++){
            string_temp = matriz_datos_apellido1.get(i).getApellido1().toString();
            list.add(string_temp);
        }
        return list;
    }


    public List<String> ObtenerApellido2(String pEmail){
        dbApellido2Pacientes db = new dbApellido2Pacientes(this,pEmail);
        db.execute("");

        matriz_datos_apellido2= db.GetMatriz();
        List<String> list = new ArrayList<String>();
        String string_temp;
        for(int i=0; i<matriz_datos_apellido2.size(); i++){
            string_temp = matriz_datos_apellido2.get(i).getApellido2().toString();
            list.add(string_temp);
        }
        return list;
    }


    public List<String> ObtenerFechaCita(String pNombre, String pApellido1, String pApellido2){
        dbFechaCita db = new dbFechaCita(this,pNombre,pApellido1,pApellido2);
        db.execute("");

        matriz_datos_fecha= db.GetMatriz();
        List<String> list = new ArrayList<String>();
        String string_temp;
        for(int i=0; i<matriz_datos_fecha.size(); i++){
            string_temp = matriz_datos_fecha.get(i).getFecha().toString();
            list.add(string_temp);
        }
        return list;
    }

    public List<String> ObtenerHoraCita(String pNombre, String pApellido1, String pApellido2){
        dbHoraCita db = new dbHoraCita(this, pNombre,pApellido1,pApellido2);
        db.execute("");

        matriz_datos_hora= db.GetMatriz();
        List<String> list = new ArrayList<String>();
        String string_temp;
        for(int i=0; i<matriz_datos_hora.size(); i++){
            string_temp = matriz_datos_hora.get(i).getHora().toString();
            list.add(string_temp);
        }
        return list;
    }
}

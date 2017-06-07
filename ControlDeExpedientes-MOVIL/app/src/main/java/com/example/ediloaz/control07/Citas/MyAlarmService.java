package com.example.ediloaz.control07.Citas;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import com.example.ediloaz.control07.R;

/**
 * Created by Administrador on 04/06/2017.
 */

public class MyAlarmService extends Service  {
    private NotificationManager mManager;
    private final Properties properties = new Properties();
    private MyAlarmService activity;

    private String password;

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
    public void enviar_email() {
        init();
        try {

            MimeMessage message = new MimeMessage(session);
            // Quien envia el correo
            message.setFrom(new InternetAddress("expedientemedicotec@gmail.com"));

            // A quien va dirigido
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("melimolinacorrales@gmail.com"));
            message.setSubject("Recordatorio de cita");
            message.setText("Buenas tardes estimado paciente\n" + "El presente correo es para recordarle que su cita es el 05/06/2017 a las 3:10 pm\n" + "Saludos");
            Transport t = session.getTransport("smtp");
            t.connect("expedientemedicotec@gmail.com","adminexpctlr");
            t.sendMessage(message,message.getAllRecipients());
            t.close();
            Toast.makeText(activity.getApplicationContext(),"Se ha configurado correctamente el recordatorio de la cita", Toast.LENGTH_LONG).show();


        } catch (Exception c) {
            Toast.makeText(activity.getApplicationContext(),"Error al enviar el recordatorio, no se pudo establecer la conexión", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        enviar_email();

    }


    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}

package com.example.ediloaz.control07.Citas;



import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Date;

/**
 * Created by Administrador on 05/06/2017.
 */

public class Dialogo_aviso extends DialogFragment {
    private Date hora_envio;
    private  boolean resultado;

    public Dialogo_aviso newInstance(Date hora, boolean result) {
        this.hora_envio = hora;
        this.resultado = result;
        return null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (resultado == true) {


            builder.setMessage("Se ha configurado correctamente el recordatorio de citas, el correo se enviará a las: " + hora_envio)
                    .setTitle("Información")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        } else {

            builder.setMessage("No se pudo configurar el recordatorio correctamente, revise su conexión a internet")
                    .setTitle("Información")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        }
        return builder.create();
    }

}

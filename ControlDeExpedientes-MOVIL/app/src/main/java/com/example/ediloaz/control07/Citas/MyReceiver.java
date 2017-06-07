package com.example.ediloaz.control07.Citas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * Created by Administrador on 04/06/2017.
 */

    public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);


    }
}

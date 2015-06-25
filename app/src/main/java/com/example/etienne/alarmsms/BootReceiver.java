package com.example.etienne.alarmsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by Etienne on 25/06/2015.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //When the phone boots when start an intentService to manage the alarms
        Log.i("mondebug", "dans le bootreceiver");
        Intent bootIntent = new Intent(context,BootService.class);
        context.startService(bootIntent);
    }
}

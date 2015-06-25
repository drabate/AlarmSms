package com.example.etienne.alarmsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Etienne on 25/06/2015.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //When the phone boots when start an intentService to manage the alarms
        Intent bootIntent = new Intent(context,BootIntentService.class);
        context.startService(bootIntent);
    }
}

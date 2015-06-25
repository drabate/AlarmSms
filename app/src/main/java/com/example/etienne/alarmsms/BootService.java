package com.example.etienne.alarmsms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BootService extends Service {
    public BootService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mondebug", "dans le bootservice pret a demarrer le thread");

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

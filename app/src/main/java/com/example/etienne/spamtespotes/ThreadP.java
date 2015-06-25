package com.example.etienne.spamtespotes;

import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Etienne on 17/05/2015.
 */
public class ThreadP extends Thread {

    private boolean running = true;
    private String numSpam;

    public ThreadP(String num)
    {
        numSpam = num;
    }

    public void arret()
    {
        running = false;
    }
    @Override
    public void run() {

        while(running)
        {
            //testDate();
            envoiSMS();
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {

            }
        }
    }

    private void testDate() {

        boolean ok = false;
        GregorianCalendar gc;

            gc = new GregorianCalendar();

            Log.i("Date du calendrier", "heure : "+ gc.get(Calendar.HOUR)+" minute : "+ gc.get(Calendar.MINUTE));
            if(gc.get(Calendar.HOUR) == 10 && gc.get(Calendar.MINUTE) == 35)
            {
                envoiSMS();
            }
    }

    private void envoiSMS()
    {
        SmsManager.getDefault().sendTextMessage(numSpam, null, "Test de mon appli de merde", null, null);
    }
}

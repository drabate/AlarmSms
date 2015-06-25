package com.example.etienne.alarmsms;

import android.telephony.SmsManager;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * one Thread by alarm
 * Created by Etienne on 17/05/2015.
 */
public class ThreadP extends Thread {

    private boolean running = true;
    private String messageAlert;
    private String num;

    public ThreadP(String message,String numero)
    {
        messageAlert = message;
        num = numero;
    }

    public void arret()
    {
        running = false;
    }
    @Override
    public void run() {

        while(running)
        {
            //SmsManager.getDefault().sendTextMessage("0614147499", null, "dans le thread: je fais mon tour dans le while", null, null);
            Log.i("mondebug","dans le thread: je fais mon tour dans le while");
            //testDate();
           // envoiSMS();
            try {
                //rappel : 1000ms = 1s
                Thread.sleep(5000);
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
        SmsManager.getDefault().sendTextMessage(num, null, "Je suis vivant :D", null, null);
    }
}

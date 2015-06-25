package com.example.etienne.alarmsms;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BootIntentService extends IntentService {

    public BootIntentService() {
        super("BootIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            for(int i=0; i <3;i++)
            {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                }
        }
    }
}

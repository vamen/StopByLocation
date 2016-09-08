package com.vivekbalachandra.android.stopbylocation;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationService extends IntentService {

    public NotificationService()
    {
        super("NotificationService");
    }
    public NotificationService(String name) {
        super(name);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("service","location with in range");
        boolean bool= intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING,false);
           if(bool)
           {

               Notification.Builder nBuilder=new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("stopByLocation").setContentText("Hey yor next stop by location is with in one kilometer");
               NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
               notificationManager.notify(101,nBuilder.build());
           }
    }
}

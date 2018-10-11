
package com.example.binder.app;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service bound to activity , if activity goes in background service will keep on running and providing counter values
 * to activity .
 */
public class MyboundService extends Service {
    private static String LOG_TAG = "MyboundService";
    private IBinder mBinder = new MyBinder();
    private int counter = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        //start the tread to do some non ui work
        MyThread th = new MyThread();
        th.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    /**
     * This function is expose to activity to give some value
     * Many other function can be written according to usage
     * @return
     */
    public int getCounter() {
        return counter;
    }

    public class MyBinder extends Binder {
        MyboundService getService() {
            return MyboundService.this;
        }
    }

    //created a thread to make repeated task, just to display UI without user intervention
    //if user wants to interact only once then no need of thread
    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            try {
            Thread.sleep(1000);
            for (int i = 1; i< 1000; i++) {
                counter = counter + 1;
                Intent intent = new Intent("UPDATE_GUI");
                //send broadcast to activity so next counter can be replaced with new one
                sendBroadcast(intent);
                Thread.sleep(1000);
            }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

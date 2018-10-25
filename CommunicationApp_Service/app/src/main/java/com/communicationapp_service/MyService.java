package com.communicationapp_service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by adpr270759 on 12-03-2018.
 */

public class MyService extends Service {

    BroadcastReceiver receiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;


    }

    @Override
    public void onCreate() {
        super.onCreate();


        Toast.makeText(this,"Service create",Toast.LENGTH_SHORT).show();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service Start",Toast.LENGTH_SHORT).show();
        receiver=new MyReciver();
        IntentFilter filter=new IntentFilter("com.communication.application");
        registerReceiver(receiver,filter);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

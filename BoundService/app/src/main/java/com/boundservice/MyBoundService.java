package com.boundservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBoundService extends Service {

    public String TAG=getClass().getName();

    public IBinder iBinder=new Mybinder();

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {


        Log.d("TAG","Service bind ");

        return iBinder;

    }

    public boolean doSomethingToReturnTrue() {

        return true;
    }

    public class Mybinder extends Binder
    {
        MyBoundService getobj()
        {
            return MyBoundService.this;
        }
    }

    public String mydate()
    {
        SimpleDateFormat formater=new SimpleDateFormat("hh:mm:ss");

        Date date=new Date();

        return formater.format(date);
    }


    @Override
    public void onCreate() {

        Log.d("TAG","Service is created");
        super.onCreate();
    }


    @Override
    public void unbindService(ServiceConnection conn) {
        Log.d("TAG","Service unbind");
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {

        Log.d("TAG","Service Distroy");
        super.onDestroy();
    }

}

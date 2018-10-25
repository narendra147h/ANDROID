package com.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.common_Cs.Icommon;


public class MyService extends Service {


    public MyService() {
    }
    private Myimpl impl=new Myimpl();

    @Override
    public IBinder onBind(Intent intent) {

        return impl;
    }

    private class Myimpl extends Icommon.Stub
    {

        @Override
        public int Add(int a, int b) throws RemoteException {
            return a+b;
        }
    }
}

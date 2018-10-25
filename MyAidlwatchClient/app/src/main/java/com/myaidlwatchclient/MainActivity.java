package com.myaidlwatchclient;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


import aidl.com.watch.Icommon;

public class MainActivity extends AppCompatActivity {
    TextView mWatch;
    Button mBindService, mUnBindService;
    Icommon mIcommon;
    ComponentName mComponentName;
    boolean mFlag = false;
    Handler mHandler =new Handler();
    ArrayList mList = new ArrayList();
    String mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWatch = (TextView) findViewById(R.id.mWatch);
        mBindService = (Button) findViewById(R.id.mBindService);
        mUnBindService = (Button) findViewById(R.id.mUnBindService);
        mBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mComponentName = new ComponentName("com.myaidlwatchserver", "com.myaidlwatchserver.Mywatchservice");
                Intent intent = new Intent();
                intent.setComponent(mComponentName);
                bindService(intent, connection, BIND_AUTO_CREATE);
                Log.d("tag", "service Bind");

            }
        });
        mUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFlag) {
                    mFlag = false;
                    unbindService(connection);
                    Log.d("tag", "service UnBind");

                }

            }
        });
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mIcommon = Icommon.Stub.asInterface(service);

            try {
                mList = (ArrayList) mIcommon.myStudentList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if (mList != null)
                Log.i("Name", String.valueOf(mList.size()));
            else
                Log.i("Name", "No Data Found");


           mFlag=true;

            Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            while (mFlag) {
                                try {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    mTime = mIcommon.myWatch();
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mWatch.setText(mTime);
                                        }
                                    });


                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    };

                    Thread thread=new Thread(runnable);
                    if(mFlag)
                        thread.start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mFlag = false;
        }
    };


}

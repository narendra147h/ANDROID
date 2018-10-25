package com.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView watch;
    Button btn,btn1;
    Boolean flag=false;
    MyBoundService.Mybinder mBinder;
    MyBoundService service;
    Thread thread;
    Runnable runnable;
    final int i=1;

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        watch = (TextView) findViewById(R.id.watch);
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);


    }

    @Override
    protected void onStart() {
        super.onStart();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MyBoundService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });

    }

    public void dounbind(View view)
        {
           if(flag)
           {
                flag=false;
               unbindService(connection);
           }


        }

    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            mBinder = (MyBoundService.Mybinder) iBinder;

            flag = true;

            //Craete a Worker thread bcz main thread is to much work if we don't take this

            runnable =new Runnable() {
                    @Override
                    public void run() {
                        while (flag) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                                service = mBinder.getobj();


                                //To update my view we only use main thread that's why we take
                                //handler

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        watch.setText(service.mydate());
                                    }
                                });





                        }

                    }
                };


                thread = new Thread(runnable);
                if(flag)
                thread.start();



            }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            flag=false;
        }
    };
}

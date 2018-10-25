package myservices.com.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adpr270759 on 09-03-2018.
 */

public class Myservice extends Service {


    /*public Myservice() {
        super(null); // That was the lacking constructor
    }
    public Myservice(String name) {
        super(name);
    }*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"My service Started..",Toast.LENGTH_LONG).show();
        Log.d("Thread value of service",Thread.currentThread().getName());


       return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this,"My service Craeted..",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {

        Toast.makeText(getApplicationContext(),"My Service Stop",Toast.LENGTH_LONG).show();

    }
}

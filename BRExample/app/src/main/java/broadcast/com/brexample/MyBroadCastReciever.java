package broadcast.com.brexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adpr270759 on 08-03-2018.
 */

public class MyBroadCastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


                Log.d("fe","am here");
                Toast.makeText(context,"Message Recived ...",Toast.LENGTH_LONG).show();
                String str=intent.getStringExtra("tag");
                Log.d("hi",str);

            }
        }




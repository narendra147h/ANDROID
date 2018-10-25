package com.communicationapp_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adpr270759 on 12-03-2018.
 */

public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Msg Recived",Toast.LENGTH_LONG).show();
        String str=intent.getStringExtra("tag");
        Log.d("tag",str);
    }
}

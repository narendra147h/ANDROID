package bush.com.intentapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "IntentData in Receiver: "+intent.getStringExtra("text"), Toast.LENGTH_LONG).show();
    }
}

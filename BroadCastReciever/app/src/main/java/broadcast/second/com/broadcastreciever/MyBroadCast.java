package broadcast.second.com.broadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyBroadCast extends AppCompatActivity {


    Button btn;
    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_broad_cast);

        btn=(Button)findViewById(R.id.btn);

        //Here I register My Broadcast Reciver

        receiver=new MyReciever();
        IntentFilter filter=new IntentFilter("com.myreciver.mybroadcast");
        registerReceiver(receiver,filter);

        //After that when we click on button an event send which recived my Reciver

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent();
                i.setAction("com.myreciver.mybroadcast");
                i.putExtra("tag","hello World");
                sendBroadcast(i);
                Log.d("tag2","send msg");
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }
}

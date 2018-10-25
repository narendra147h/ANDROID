package broadcast.com.brexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class BraodCastReciveActivity extends AppCompatActivity {


    Button clickme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braod_cast_recive);





        clickme=(Button)findViewById(R.id.clickme);

        clickme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.putExtra("tag","recieve");
                i.setAction("com.myreciver.mybroadcast");
                Log.d("hello","hiii");
                sendBroadcast(i);


            }
        });
    }


}

package bush.com.intentapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEditText;
    ImageView mImageView;
    private static final String TAG = "LocalBroadcastDemo";
    private IntentFilter receiveFilter;
    BoundService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiveFilter = new IntentFilter(getClass().getName());
        LocalBroadcastManager.getInstance(this).
                registerReceiver(handler, receiveFilter);

        mEditText = findViewById(R.id.sample_text);
        mImageView = findViewById(R.id.image);

        Button startActivity = findViewById(R.id.explicit_intent);
        startActivity.setOnClickListener(this);

        Button startService = findViewById(R.id.start_service);
        startService.setOnClickListener(this);

        Button boundService = findViewById(R.id.bound_service);
        boundService.setOnClickListener(this);

        Button sendBroadcast = findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(this);

        Button localBroadcast = findViewById(R.id.local_broadcast);
        localBroadcast.setOnClickListener(this);

        Button implicitIntent = findViewById(R.id.implicit_intent);
        implicitIntent.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(handler);
        super.onPause();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.explicit_intent:
                Intent activityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                activityIntent.putExtra("text", mEditText.getText().toString());
                startActivity(activityIntent);
                break;

            case R.id.start_service:
                Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
                serviceIntent.putExtra("text", mEditText.getText().toString());
                startService(serviceIntent);
                break;

            case R.id.bound_service:
                Intent intent = new Intent(this, BoundService.class);
                intent.putExtra("text", mEditText.getText().toString());
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.send_broadcast:
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("bush.com.intentapp.A_CUSTOM_INTENT");
                broadcastIntent.putExtra("text", mEditText.getText().toString());
                sendBroadcast(broadcastIntent);
                break;

            case R.id.local_broadcast:
                Intent sendableIntent = new Intent(getClass().getName());
                LocalBroadcastManager.getInstance(this).sendBroadcast(sendableIntent);
                break;

            case R.id.implicit_intent:
                Intent implicitIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                implicitIntent.setType("image/*");
                implicitIntent.putExtra("crop", "true");
                implicitIntent.putExtra("scale", true);
                implicitIntent.putExtra("outputX", 256);
                implicitIntent.putExtra("outputY", 256);
                implicitIntent.putExtra("aspectX", 1);
                implicitIntent.putExtra("aspectY", 1);
                implicitIntent.putExtra("return-data", true);
                startActivityForResult(implicitIntent, 1);
                break;

            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = intent.getExtras();
            if (extras != null) {
                //Get image
                Bitmap picBitMap = extras.getParcelable("data");
                mImageView.setImageBitmap(picBitMap);
            }
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;

            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };

    private BroadcastReceiver handler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "MainActivity.handler.new BroadcastReceiver() {...}.onReceive(): " + Thread.currentThread());
            Toast.makeText(MainActivity.this, "LocalBroadcast Received", Toast.LENGTH_LONG).show();
        }
    };
}
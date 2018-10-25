package com.myhandlerapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button download;
    ProgressBar progress;
    TextView preport;
    ImageView img;
    //Handler handler=new Handler();


    //URL url=new URL("https://static.pexels.com/photos/15239/flower-roses-red-roses-bloom.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       // StrictMode.setThreadPolicy(policy);

        download=(Button)findViewById(R.id.download);
        progress=(ProgressBar)findViewById(R.id.progress);
        preport=(TextView) findViewById(R.id.preport);
        img=(ImageView)findViewById(R.id.img);

        //Set Url of image

       final String url="https://unsplash.com/photos/e6s-DmL7D3A/download?force=true";

        download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                preport.setText("Downloading Start...");
                img.setTag(url);
                Log.d("msg1", String.valueOf(Thread.currentThread().getId()));
                startDownload();


            }
        });

    }
    public void startDownload()
    {
        Runnable runnable=new Runnable() {

            @Override
            public void run() {



                for (int i=0;i<=10;i++)
                {
                    final int value=i;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   /* handler.post(new Runnable() {
                        @Override
                        public void run() {



                            preport.setText("Time left ..."+(10-value));
                            progress.setProgress(value);
                            if(value==10)
                            {
                                Log.d("msg", String.valueOf(Thread.currentThread().getId()));
                                img.setImageBitmap(download_Image((String)img.getTag()));
                                preport.setText("Download completed");
                            }
                        }
                    });*/

                    progress.post(new Runnable() {
                        @Override
                        public void run() {

                            preport.setText("Time left ..."+(10-value));
                            progress.setProgress(value);
                            if(value==10)
                            {
                                Log.d("msg", String.valueOf(Thread.currentThread().getId()));
                                img.setImageBitmap(download_Image((String)img.getTag()));
                                preport.setText("Download completed");
                            }
                        }
                    });
                }

            }
        };

        new Thread(runnable).start();



    }
    private Bitmap download_Image(String url) {
        //---------------------------------------------------
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
        }
        return bm;
        //---------------------------------------------------
    }

}

package com.asynchtask_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView preport;
    Button download;
    ProgressBar progress;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download=(Button)findViewById(R.id.download);
        progress=(ProgressBar)findViewById(R.id.progress);
        preport=(TextView) findViewById(R.id.preport);
        img=(ImageView)findViewById(R.id.img);


        //Url Information

        String url="https://unsplash.com/photos/Z-4kOr93RCI/download?force=true";
        img.setTag(url);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask task=new MyAsyncTask();
                task.execute(img);
            }
        });



    }

    private class MyAsyncTask extends AsyncTask<ImageView,Integer,Bitmap>
    {
        ImageView imageView = null;

        @Override
        protected void onPreExecute() {
            preport.setText("Downloding is started..");
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(ImageView...imageViews) {

            for (int i=0;i<=10;i++)
            {
                final Integer value=i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.imageView = imageViews[0];

               publishProgress(value);
            }

            return download_Image((String)imageView.getTag());
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

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
            int temp=values[0];
            preport.setText("Time left : "+(10-temp));

        }

        @Override
        protected void onPostExecute(Bitmap s) {
            preport.setText("Downloding Completed..");
            imageView.setImageBitmap(s);
            super.onPostExecute(s);
        }
    }
}


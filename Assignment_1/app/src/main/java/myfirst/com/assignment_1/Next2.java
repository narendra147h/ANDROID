package myfirst.com.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Next2 extends AppCompatActivity {


    RatingBar rating;
    TextView tv,tv1;
    int data=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);


        rating=(RatingBar)findViewById(R.id.rating);
        tv=(TextView)findViewById(R.id.tv);
        tv1=(TextView)findViewById(R.id.tv2);

        tv1.setText("Please Rate My App");
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tv.setText("Thanks for your support and your rating is "+v);
                data=(int)v;
                rating.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    public void onBackPressed() {



        Intent intent=new Intent();
       // System.out.println(data);
        //Bundle b=new Bundle();
        //b.putFloat("Rating",data);
        intent.putExtra("rating",data);
        setResult(RESULT_OK, intent);
        finish();
    }
}

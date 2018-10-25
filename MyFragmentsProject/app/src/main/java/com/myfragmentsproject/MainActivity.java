package com.myfragmentsproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements ActivityTOFragment {



    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      answer=(TextView)findViewById(R.id.answer);

    }


    @Override
    public int sendMessage(int a) {
        answer.setText(String.valueOf(a));
        return 0;
    }
}

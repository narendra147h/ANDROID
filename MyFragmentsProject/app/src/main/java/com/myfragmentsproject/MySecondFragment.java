package com.myfragmentsproject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MySecondFragment extends Fragment {

    ActivityTOFragment activityTOFragment;


    public MySecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity=(Activity)context;
        activityTOFragment=(ActivityTOFragment)activity;
        Log.d("Attach","Fragment attached to activity");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create","Fragment created");
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("View","Fragment view is craeted");
        View view;
        view= inflater.inflate(R.layout.fragment_my_second, container, false);

        final EditText num3,num4;
        Button mul;

        num3=(EditText)view.findViewById(R.id.num3);
        num4=(EditText)view.findViewById(R.id.num4);
        mul=(Button) view.findViewById(R.id.mul);

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1,n2,flag=0,result;

                if(num3.getText().toString().equals(""))
                {
                    flag=1;
                    n1=0;
                }
                else
                {
                    try{
                        n1=Integer.parseInt(num3.getText().toString());
                        flag=1;
                    }
                    catch (NumberFormatException e)
                    {
                        flag=0;
                        n1=0;

                    }
                }
                 if(num4.getText().toString().equals(""))
                 {
                     flag=1;
                     n2=0;
                 }
                 else
                 {
                     try{
                         n2=Integer.parseInt(num4.getText().toString());
                         flag=1;
                     }
                     catch (NumberFormatException e)
                     {
                         flag=0;
                         n2=0;

                     }

                 }

                 if(flag==1)
                 {
                     result=n1*n2;
                     activityTOFragment.sendMessage(result);
                 }
                 else {
                     Toast.makeText(getActivity(),"Please Enter number only",Toast.LENGTH_LONG).show();
                 }



            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Log.d("created","OnActivity created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("start","onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resume","Fragment Resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("pause","Fragment pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Stop","Fragment Stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ViewD","onDestroyView() is called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("destroy","Fragment Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Detach","Fragment detach");
    }
}



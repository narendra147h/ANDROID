package com.myaidlwatchserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Calendar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aidl.com.watch.Icommon;
import myclass.Student;


public class Mywatchservice extends Service {


    Student mStudent1 = new Student("XYZ",13301,"CdacActs");
    Student mStudent2 = new Student("Shahu",13302,"Cdac");
    Student mStudent3 = new Student("Nithesh",13303,"kt");
    Student mStudent4 = new Student("Aditya",13304,"Acts");

    ArrayList<Student> mStudents = new ArrayList<>();

    public void onInit()
    {
        mStudents.add(mStudent1);
        mStudents.add(mStudent2);
        mStudents.add(mStudent3);
        mStudents.add(mStudent4);
    }


    MyInner mIBinder =new MyInner();

    public Mywatchservice() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        onInit();
        return mIBinder;
    }

    public class MyInner extends Icommon.Stub
    {


        @Override
        public List myStudentList() throws RemoteException
        {
            return mStudents;
        }

        @Override
        public String myWatch() {
            Date currentTime = Calendar.getInstance().getTime();
            return currentTime.toString();
        }
    }


}

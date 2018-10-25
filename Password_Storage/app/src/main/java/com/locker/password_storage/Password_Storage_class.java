package com.locker.password_storage;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adpr270759 on 03-04-2018.
 */

public class Password_Storage_class extends SQLiteOpenHelper {


    private static final String DATA_BASE ="password_storage_container.db";
    private static final String TABLE_NAME ="password_container";
    private static final String ID="id";
    private static final String PASSWORD_FOR ="password_for";
    private static final String PASSWORD="password";
    private static final String EMAIL="email_id";
    private static final int VERSION=1;
    private String mEmail;

    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                                                +EMAIL+" TEXT NOT NULL , "+PASSWORD_FOR+" TEXT NOT NULL , "
                                                +PASSWORD+" TEXT NOT NULL ) ";


    public Password_Storage_class(Context context) {

        super(context,DATA_BASE,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}

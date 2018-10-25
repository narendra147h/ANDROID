package com.collection.garbage.awms;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView loginstatus;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        loginstatus = (TextView) findViewById(R.id.loginstatus);

        Intent intent = getIntent();

        mUserName = intent.getStringExtra("Name");

        loginstatus.setText("Welcome "+mUserName);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mToggle = new ActionBarDrawerToggle(this , mDrawerLayout , R.string.open , R.string.close);


        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        //this is for creating a toggle button view
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Setting Navigable Layout
        setNavigableLayout();


    }

    /**
     * Setting Navigable View
     */
    private void setNavigableLayout() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigable);

        navigationView.setNavigationItemSelectedListener(this);


    }

    /**
     *
     * This method is enable the toggle button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Click event for clicking navigable drawer
     *
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Home :
            {
                Intent intent = new Intent(this,Profile.class);
                intent.putExtra("Name",mUserName);
                startActivity(new Intent(intent));
             break;
            }
            case R.id.Setting :
            {
                break;
            }
            case R.id.MyAccount :
            {
                break;
            }
            case R.id.Rewards :
            {
                break;
            }
            case R.id.History :
            {
                break;
            }
            case R.id.Logout :
            {
                Log.d("dsf","clicked");
                startActivity(new Intent(this,Login.class));
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,Login.class));
        super.onBackPressed();
    }
}



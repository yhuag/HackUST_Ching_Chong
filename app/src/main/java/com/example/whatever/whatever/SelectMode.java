package com.example.whatever.whatever;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import whatever.whatever.CardViewActivity1;
import whatever.whatever.DecoderActivity;
import whatever.whatever.R;

public class SelectMode extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button scanBtn = (Button) findViewById(R.id.QR_button);
        scanBtn.setOnClickListener(this);
        Button randBtn = (Button) findViewById(R.id.complete_random_button);
        randBtn.setOnClickListener(this);
        Button visitedBtn = (Button) findViewById(R.id.visited_button);
        visitedBtn.setOnClickListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public  void onClick(View v){
        if(v.getId()==R.id.QR_button){
            Intent intent = new Intent(this, DecoderActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.complete_random_button)
        {
            Intent intent = new Intent(this, com.example.whatever.whatever.Detail.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.visited_button)
        {
            Intent intent = new Intent(this, whatever.whatever.DataActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_mode, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_visited) {
            Intent intent = new Intent(this,CardViewActivity1.class);
            startActivity(intent);
        } else if (id == R.id.nav_instruction) {
            Intent intent = new Intent(this,Instruction.class);
            startActivity(intent);

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this,Setting.class);
            startActivity(intent);


        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(this,Feedback.class);
            startActivity(intent);
        }else if (id == R.id.nav_myPick) {
            Intent intent = new Intent(this,MyPick.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_pick) {
            Intent intent = new Intent(this,SelectMode.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

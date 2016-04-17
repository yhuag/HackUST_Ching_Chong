package com.example.whatever.whatever;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import whatever.whatever.CardViewActivity1;
import whatever.whatever.R;

public class Detail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Detail detail;
    int counter;
    public Detail() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        counter = 0;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        final List<String[]> contentList = new LinkedList<String[]>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.first);


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
        getMenuInflater().inflate(R.menu.detail, menu);
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

    private ArrayList<ContentObject> getDataSet() {
        ArrayList results = new ArrayList<ContentObject>();
        //***************************index<length of database!**************

            String[] content = new String[4];
            content[0] = "Lucky Indonesian Restaurant";
            content[1] = "G/F, 46 Tung Ming Street, Kwun Tong";
            content[2] = "Indonesian";
            content[3] = "1-100";

            ContentObject obj = new ContentObject("Name",content[0]);
            results.add(0,obj);
            obj = new ContentObject("Address",content[1]);
            results.add(1,obj);
            obj = new ContentObject("Style",content[2]);
            results.add(2,obj);
            obj = new ContentObject("Price",content[3]);
            results.add(3,obj);


        return results;
    }
    public void setContent(ArrayList<ContentObject> arrayList)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(arrayList);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void noClick(View view) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_menu_gallery);
        builderSingle.setTitle("Add more item");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_singlechoice);

        arrayAdapter.add("Too Expensive");
        arrayAdapter.add("Too Far");
        arrayAdapter.add("Not this type");


        builderSingle.setNegativeButton(
                "cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        counter++;
                        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                        String[] content = ContentGenerator.generateContent(counter);
                        ArrayList results = new ArrayList<ContentObject>();
                        if(counter == 0)
                        {
                            imageView.setImageResource(R.drawable.first);

                        }
                        else if(counter==1)
                        {
                            imageView.setImageResource(R.drawable.second);
                        }
                        else
                        {
                            imageView.setImageResource(R.drawable.third);
                        }

                        ContentObject obj = new ContentObject("Name",content[0]);
                        results.add(0,obj);
                        obj = new ContentObject("Address",content[1]);
                        results.add(1,obj);
                        obj = new ContentObject("Style",content[2]);
                        results.add(2,obj);
                        obj = new ContentObject("Price",content[3]);
                        results.add(3,obj);
                        setContent(results);


                    }
                });
        builderSingle.show();
    }

    public void yesClick(View view) {
        Intent  intent = new Intent(this,  CardViewActivity1.class);
        startActivity(intent);
    }
}

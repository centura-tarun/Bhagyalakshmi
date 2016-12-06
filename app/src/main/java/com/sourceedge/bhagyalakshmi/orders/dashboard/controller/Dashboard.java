package com.sourceedge.bhagyalakshmi.orders.dashboard.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sourceedge.bhagyalakshmi.orders.R;
import com.sourceedge.bhagyalakshmi.orders.support.Class_Genric;

public class Dashboard extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle mDrawerToggle;
    SharedPreferences sharedPreferences;
    ScrollView dashboard_scrollview;
    TextView total_order_count;
    CardView totalOrder,adminRetailersDistributors,statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Class_Genric.setOrientation(Dashboard.this);
        sharedPreferences = getSharedPreferences(Class_Genric.MyPref, Dashboard.MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bhagyalakshmi Traders");
        setSupportActionBar(toolbar);
        Class_Genric.applyFontForToolbarTitle(toolbar,Dashboard.this);
        drawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        total_order_count=(TextView)findViewById(R.id.total_order_count);
        animateTextView(0,157,total_order_count);

        dashboard_scrollview=(ScrollView)findViewById(R.id.dashboard_scrollview);

        totalOrder=(CardView)findViewById(R.id.total_order);   //
        adminRetailersDistributors=(CardView)findViewById(R.id.admin_retailers_distributors);

        statistics=(CardView)findViewById(R.id.statistics);//
        onClicks();
        Class_Genric.setupDrawer(toolbar,drawer,mDrawerToggle,Dashboard.this);
        Class_Genric.drawerOnClicks(Dashboard.this);

        switch (Class_Genric.getType(sharedPreferences.getString(Class_Genric.Sp_LoginType, ""))) {
            case Class_Genric.ADMIN:
                break;
            case Class_Genric.DISTRIBUTORSALES:
                break;
            case Class_Genric.DISTRIBUTOR:

                break;
            case Class_Genric.SALESPERSON:
                break;
        }

    }

    private void onClicks() {
        dashboard_scrollview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void animateTextView(int initialValue, int finalValue, final TextView textview) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.5f);
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (int count = start; count <= end; count++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) count) / difference)) * 20) * count;
            final int finalCount = ((initialValue > finalValue) ? initialValue - count : count);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textview.setText(finalCount + "");
                }
            }, time);
        }
    }

}

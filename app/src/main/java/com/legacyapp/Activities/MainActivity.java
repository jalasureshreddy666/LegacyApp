package com.legacyapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.legacyapp.Adapters.MyPagerAdapter;
import com.legacyapp.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private View line_divider, toolbar_shadow;
    private TabLayout tabs ,tab_bottom;
    private RelativeLayout view_search;
    private Resources res;

    MyPagerAdapter pagerAdapterToolbarSearch;
    public static ViewPager viewPager;
    private Context context;
    private Activity activity;
    int tabvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
     //   toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_color));

        context = getApplicationContext();
        activity = this;

        Intent intent=getIntent();
        if(intent!=null)
        {
           tabvalue = intent.getIntExtra("tabvalue",0);
        }



        tabs = (TabLayout) findViewById(R.id.tabs);
        tab_bottom = (TabLayout)findViewById(R.id.tool);




        tab_bottom.setTabTextColors(Color.parseColor("#CCffffff"), Color.parseColor("#E12026"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_view_list_black_48dp).setText("Catalog"));
        //tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.qrcode).setText("QRCode"));
       // tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_cached_black_48dp).setText("Sync"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_person_black_48dp).setText("Me"));
      //  tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_more_horiz_white_48dp).setText("More"));


        tab_bottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab_bottom.getSelectedTabPosition() == 0)
                {
                    //Toast.makeText(MainActivity.this, "Tab " + tab_bottom.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                }

              /* else if(tab_bottom.getSelectedTabPosition() == 1)
                {
                    Intent i = new Intent(MainActivity.this,Barcodereader.class);
                  //  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                   // finish();

                }*/

           /*     else if(tab_bottom.getSelectedTabPosition() == 2)
                {
                    //Toast.makeText(MainActivity.this, "Tab " + tab_bottom.getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                }*/

                else if(tab_bottom.getSelectedTabPosition() == 1)
                {
                    Intent i = new Intent(MainActivity.this, Tab_layout.class);
                  //  i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                   // finish();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       // view_search = (RelativeLayout) findViewById(R.id.view_search);
       // line_divider = findViewById(R.id.line_divider);
        //toolbar_shadow = findViewById(R.id.toolbar_shadow);

        tabs.setTabTextColors(Color.parseColor("#CCffffff"), Color.parseColor("#E12026"));
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapterToolbarSearch = new MyPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterToolbarSearch);

        tabs.setupWithViewPager(viewPager);

        TabLayout.Tab tab = tabs.getTabAt(tabvalue);
        tab.select();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
       // drawer.removeDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

     /*   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //char id1=String.valueOf(id).charAt(9);

        if (id == R.id.nav_paper)
        {
            Intent i=new Intent(this, HealthCareItems.class);
            i.putExtra("url","http://178.62.187.32/legacycon/?cid=1");
            startActivity(i);

        }
        else if (id == R.id.nav_dry)
        {
            Intent i=new Intent(this, HealthCareItems.class);
            i.putExtra("url","http://178.62.187.32/legacycon/?cid=2");
            startActivity(i);

        } else if (id == R.id.nav_wet)
        {
            Intent i=new Intent(this, HealthCareItems.class);
            i.putExtra("url","http://178.62.187.32/legacycon/?cid=3");
            startActivity(i);
        }
        else if (id == R.id.nav_tablet)
        {
            Intent i=new Intent(this, HealthCareItems.class);
            i.putExtra("url","null");
            startActivity(i);
        }
        else if (id == R.id.nav_label)
        {
            Intent i=new Intent(this, HealthCareItems.class);
            i.putExtra("url","null");
            startActivity(i);
        }
        /*else if (id == R.id.nav_tech)
        {
            viewPager.setCurrentItem(5);
        }
        else if (id == R.id.track_order)
        {

        }
        else if (id == R.id.login){

            Intent i = new Intent(MainActivity.this, Tab_layout.class);
            startActivity(i);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        /*finish();
        startActivity(getIntent());*/
    }
}

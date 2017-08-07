package com.legacyapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.legacyapp.Network.SendDeviceDetails;
import com.legacyapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Tab_layout extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabLayout tab_bottom;
    private EditText companyname,address1,address2,phone,email,password;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);

       toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tab_bottom = (TabLayout)findViewById(R.id.tool);

        companyname=(EditText)findViewById(R.id.companyname);
        address1=(EditText)findViewById(R.id.address1);
        address2=(EditText)findViewById(R.id.address2);
        phone=(EditText)findViewById(R.id.phonenumber);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        send=(Button) findViewById(R.id.samples);

        tab_bottom.setTabTextColors(Color.parseColor("#CCffffff"), Color.parseColor("#E12026"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_view_list_black_48dp).setText("Catalog"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_person_black_48dp).setText("Me"));

        TabLayout.Tab tab = tab_bottom.getTabAt(1);
        tab.select();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                JSONObject postData = new JSONObject();
                try {
                    postData.put("name", companyname.getText().toString());
                    postData.put("address1", address1.getText().toString());
                    postData.put("address2", address2.getText().toString());
                    postData.put("phone", phone.getText().toString());
                    postData.put("email", email.getText().toString());
                    postData.put("password", password.getText().toString());

                    new SendDeviceDetails().execute(" ", postData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tab_bottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab_bottom.getSelectedTabPosition() == 0)
                {
                    Intent i = new Intent(Tab_layout.this, MainActivity.class);
                    startActivity(i);
                }

                else if(tab_bottom.getSelectedTabPosition() == 1)
                {


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



}

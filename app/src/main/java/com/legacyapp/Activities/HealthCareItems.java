package com.legacyapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.legacyapp.Adapters.CustomListViewAdapter;
import com.legacyapp.Utils.ConstantClass;
import com.legacyapp.Models.RowItem;
import com.legacyapp.Models.TopModels;
import com.legacyapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HealthCareItems extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    ArrayList<RowItem> rowItems;
    EditText editText;

    ImageView imageView;
    private ProgressDialog dialog;
    String url;
    public static ArrayList<String> productid = new ArrayList<>();
    public static ArrayList<String> productname = new ArrayList<>();
    public static ArrayList<String> productdescription = new ArrayList<>();
    public static ArrayList<String> productimage = new ArrayList<>();
    public static ArrayList<String> brochure_file = new ArrayList<>();
    public static ArrayList<String> msds_file = new ArrayList<>();
    String error = null;
    CustomListViewAdapter adapter;

    private TabLayout tabs ,tab_bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);
        list = (ListView) findViewById(R.id.listView);
        editText=(EditText)findViewById(R.id.search_view);
        imageView=(ImageView)findViewById(R.id.img);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        int res = intent.getExtras().getInt("image");
        int tabvalue=intent.getExtras().getInt("tabvalue");

        imageView.setImageResource(res);
        System.out.println("url====" + url);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tab_bottom = (TabLayout)findViewById(R.id.tool);

        tab_bottom.setTabTextColors(Color.parseColor("#CCffffff"), Color.parseColor("#E12026"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_view_list_black_48dp).setText("Catalog"));
        tab_bottom.addTab(tab_bottom.newTab().setIcon(R.mipmap.ic_person_black_48dp).setText("Me"));


        tab_bottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab_bottom.getSelectedTabPosition() == 0)
                {

                }
                else if(tab_bottom.getSelectedTabPosition() == 1)
                {
                    Intent i = new Intent(HealthCareItems.this, Tab_layout.class);
                    startActivity(i);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabs.setTabTextColors(Color.parseColor("#CCffffff"), Color.parseColor("#E12026"));
        tabs.addTab(tabs.newTab().setText("TOP"));
        tabs.addTab(tabs.newTab().setText("WORKPLACE"));
        tabs.addTab(tabs.newTab().setText("HOSPITALITY"));
        tabs.addTab(tabs.newTab().setText("FOOD SERVICE"));
        tabs.addTab(tabs.newTab().setText("TECHNOLOGY"));
        tabs.addTab(tabs.newTab().setText("HEALTHCARE"));

        TabLayout.Tab tab = tabs.getTabAt(tabvalue);
        tab.select();

        tabs.setScrollPosition(tabvalue,0f,true);


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabs.getSelectedTabPosition() == 0)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",0);
                    startActivity(i);

                }
                else if(tabs.getSelectedTabPosition() == 1)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",1);
                    startActivity(i);
                }
                else if(tabs.getSelectedTabPosition() == 2)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",2);
                    startActivity(i);

                }
                else if(tabs.getSelectedTabPosition() == 3)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",3);
                    startActivity(i);
                }
                else if(tabs.getSelectedTabPosition() == 4)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",4);
                    startActivity(i);

                }
                else if(tabs.getSelectedTabPosition() == 5)
                {
                    Intent i=new Intent(HealthCareItems.this,MainActivity.class);
                    i.putExtra("tabvalue",5);
                    startActivity(i);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Data data = new Data();
        data.execute();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {


            }

            @Override
            public void afterTextChanged(Editable editable)
            {

                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

       System.out.println("clickeddddd===" +i);
        adapter.selectedItem(i);
        adapter.notifyDataSetChanged();
    }

    private class Data extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(HealthCareItems.this);
            dialog.setIndeterminate(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (url != null & !url.equals("null")) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();

                try {
                    TopModels models = new TopModels();
                    productdescription.clear();
                    Response response = client.newCall(request).execute();
                    String output = response.body().string();
                    System.out.println("RESULT : " + output);

                    if (output != null) {
                        try {
                            Object json = new JSONTokener(output).nextValue();
                            if (json instanceof JSONObject) {
                                JSONObject myJson = new JSONObject(output);
                                error = myJson.getString("error_msg");
                                System.out.println("error=====" + error);
                                // productimage.add(object.getString(ConstantClass.product_image));
                            } else if (json instanceof JSONArray)
                            {
                                JSONArray jsonObj = new JSONArray(output);

                                for (int i = 0; i < jsonObj.length(); i++)
                                {

                                    JSONObject object = jsonObj.getJSONObject(i);

                                      models.setProductTypeId(object.getString(ConstantClass.productId));
                                      productid.add(object.getString(ConstantClass.productId));

                                      models.setproduct_name(object.getString(ConstantClass.pname));
                                      productname.add(object.getString(ConstantClass.pname));

                                      models.setProd_desc(object.getString(ConstantClass.product_description));
                                      productdescription.add(object.getString(ConstantClass.product_description));

                                      models.setproduct_image(object.getString(ConstantClass.product_image));
                                      productimage.add(object.getString(ConstantClass.product_image));

                                      brochure_file.add(object.getString(ConstantClass.brochure_file));
                                      models.setMsds(object.getString(ConstantClass.msds_file));

                                      msds_file.add(object.getString(ConstantClass.msds_file));

                                      models.setProduct(productid);
                                      System.out.println("pid=====" + object.getString(ConstantClass.productId) + "image===" + object.getString(ConstantClass.product_image));
                                  }
                                }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            rowItems = new ArrayList<RowItem>();
            rowItems.clear();
            RowItem item;
            if (error == null & !url.equals("null")) {
                for (int i = 0; i < productdescription.size(); i++) {
                     item = new RowItem(productimage.get(i), productname.get(i), productdescription.get(i),productimage.get(i),brochure_file.get(i),msds_file.get(i));
                     rowItems.add(item);
                }
            } else {
                item = new RowItem("", "Product not available", "","","","");
                rowItems.add(item);
            }

             adapter = new CustomListViewAdapter(HealthCareItems.this, R.layout.health_items, rowItems);
             list.setAdapter(adapter);

            list.setOnItemClickListener(HealthCareItems.this);



          /*  list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    System.out.print("clicked===="+i);
                }
            });*/
        }

    }



}

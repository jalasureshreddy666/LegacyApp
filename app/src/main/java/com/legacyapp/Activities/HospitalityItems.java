package com.legacyapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.legacyapp.Adapters.CustomListViewAdapter;
import com.legacyapp.Models.RowItem;
import com.legacyapp.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalityItems extends AppCompatActivity  {

    ListView list;
    ProgressDialog mProgressDialog;
    List<RowItem> rowItems;

    public static final String[] titles = new String[] { "Towel 1",
            "Towel 2", "Food Service Towel", "Pillows", "Syringe" };

    public static final String[] descriptions = new String[] {
            "It is Everwipe's Chem-Ready material ", "It is Everwipe's Chem-Ready material",
            "It is Food Service Towel", "It is Pillows",
            "It is Syringe" };

    public static final int[] images = new int[] { R.drawable.pic_1,
            R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_6, R.drawable.pic_11 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitality_items);

        list = (ListView)findViewById(R.id.listView);

        rowItems = new ArrayList<RowItem>();
      /*  for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }*/

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.hospitality_items, rowItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.title);
                TextView textView1 = (TextView)view.findViewById(R.id.desc);
                //ImageView img = (ImageView) view.findViewById(R.id.icon);

                String txt = textView.getText().toString();
                String txt1 = textView1.getText().toString();

                Intent i = new Intent(HospitalityItems.this, Productview.class);
                i.putExtra("key", txt);
                i.putExtra("key1", txt1);
               // i.putExtra("img",images[position]);
                startActivity(i);
            }
        });

    }

}

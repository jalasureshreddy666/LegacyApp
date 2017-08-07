package com.legacyapp.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;


import com.legacyapp.Adapters.ModelsAdapter;
import com.legacyapp.Models.TopModels;
import com.legacyapp.Activities.MainActivity;
import com.legacyapp.Network.NetworkUtil;
import com.legacyapp.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Home extends Fragment {

    private View v;
    private Context context;
    private RecyclerView recyclerView;
    private ModelsAdapter mAdapter;
    private ArrayList<TopModels> models = new ArrayList<>();
    GridView gridView;
    //private GridAdapter gridAdapter;
    private String pro_name, pro_id, pro_image, pro_desc, pro_benf;
    //public static ArrayList<HashMap<String, String>> AllList;
    private ArrayList<TopModels> AllList;
    //private ArrayList<HashMap<String, String>> modelist = new ArrayList<>();
    private ProgressDialog dialog;
    private Fragment f = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.home_fragment, container, false);

        context = getActivity();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_vieV);
        int network_status = NetworkUtil.getConnectivityStatus(context);
        if(network_status==0){
            Toast toast = Toast.makeText(context , "Please Check Your Internet Connection" , Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        } else {
            /*GetData data = new GetData();
            data.execute();*/

            GridLayoutManager  mLayoutManager = new GridLayoutManager(context, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setHasFixedSize(true);
            mAdapter = new ModelsAdapter(context);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();


            mAdapter.setOnItemClickListener(new ModelsAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                  Log.d(TAG, "onItemClickonItemClick position: " + position);
                    if (position == 0) {
                        MainActivity.viewPager.setCurrentItem(5);
                    }
                    else if (position == 1) {
                        MainActivity.viewPager.setCurrentItem(3);
                    }
                    else if (position == 2) {
                        MainActivity.viewPager.setCurrentItem(1);
                    }
                    else if (position == 3) {
                        MainActivity.viewPager.setCurrentItem(2);
                    }
                    else if (position == 4) {
                        MainActivity.viewPager.setCurrentItem(4);
                    } 
                    }

                @Override
                public void onItemLongClick(int position, View v) {
                    Log.d(TAG, "onItemLongClick pos = " +position);
                }
            });
        }


        AllList = new ArrayList<>();
        return v;
    }

}

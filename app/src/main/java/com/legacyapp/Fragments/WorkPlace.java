package com.legacyapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.legacyapp.Activities.HealthCareItems;
import com.legacyapp.Models.TopModels;
import com.legacyapp.Adapters.ExpandableListAdapter;
import com.legacyapp.Utils.ConstantClass;
import com.legacyapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HOME on 26/5/2016.
 */
public class WorkPlace extends Fragment
{
    private View v;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    private int lastExpandedPosition = -1;

    private Context context;
    private ProgressDialog dialog;
    //   private ArrayList<TopModels> AllList;
    private String industryname;
    private String industrynamelist;

    LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();
    LinkedHashMap<String, List<String>> expandableListDetailnew = new LinkedHashMap<String, List<String>>();
    ArrayList<String> id1=new ArrayList<>();
    ArrayList<String> id2=new ArrayList<>();

    List<String> expandableListTitle;
    List<String> expandableListTitlenew;

    List<String> expandableListid;

    LinkedHashMap<String, List<String>> expandableListDetailnewsecond = new LinkedHashMap<String, List<String>>();

    int imagesworkplace[]={R.drawable.manufacturing,R.drawable.automotive,R.drawable.marine,R.drawable.education,R.drawable.office,R.drawable.government,R.drawable.pharma,R.drawable.aviation};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.work_place,container,false);
        context = getActivity();

        expandableListView=(ExpandableListView)v.findViewById(R.id.expandableListViewWorkPlace);

        GetDataList data = new GetDataList();
        data.execute();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {

               /* Intent i = new Intent(getActivity(), FoodServiceItems.class);
                startActivity(i);*/

                String idnew=id1.get(groupPosition);
                String selectedid=expandableListDetailnewsecond.get(expandableListid.get(groupPosition)).get(childPosition);
                System.out.println("id===="+idnew+"child postion"+childPosition+"long"+id+"iddddd"+selectedid);
                String url="http://178.62.187.32/legacycon/?indID="+idnew+"&ptID="+selectedid;
                final int gp=groupPosition;
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        expandableListView.collapseGroup(gp);
                    }
                },500);

                Intent i = new Intent(getActivity(), HealthCareItems.class);
                i.putExtra("url",url);
                i.putExtra("image",R.drawable.workplace_full);
                i.putExtra("tabvalue",1);
                startActivity(i);

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        return v;

    }

    private class GetDataList extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(context);
            dialog.setIndeterminate(false);
            dialog.setMessage("Loading......");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url="3";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(ConstantClass.Tab_url+url).build();


                Response response = client.newCall(request).execute();
                String output = response.body().string();
                System.out.println("RESULT : " + output);

                if (output != null) {
                    try {
                        JSONArray jsonObj = new JSONArray(output);

                        for (int i = 0; i < jsonObj.length(); i++) {
                            JSONObject object = jsonObj.getJSONObject(i);
                            TopModels models = new TopModels();

                            models.setIndustries_name(object.getString(ConstantClass.industries_name));
                            industryname=object.getString(ConstantClass.industries_name);
                            models.setIndustriesid(object.getString(ConstantClass.industriesid));
                            models.setParentId(object.getString(ConstantClass.parentId));

                            Request request_sub = new Request.Builder().url("http://178.62.187.32/legacycon/?indId="+object.getString(ConstantClass.industriesid)).build();

                            Response responselist = client.newCall(request_sub).execute();
                            String outputlist = responselist.body().string();
                            System.out.println("RESULT : " + outputlist);
                            JSONObject object1 = null;
                            List<String> first =new ArrayList<String>();
                            List<String>  second =new ArrayList<String>();
                            if (outputlist != null)
                            {
                                JSONArray json = new JSONArray(outputlist);
                                for(int j = 0; j < json.length(); j++)
                                {
                                    object1=json.getJSONObject(j);
                                    models.setIndustries_namelist(object1.getString(ConstantClass.industries_name));
                                    industrynamelist = object1.getString(ConstantClass.industries_name);
                                    if(industryname.equals(industrynamelist))
                                    {
                                        models.setProductTypeId(object1.getString(ConstantClass.productTypeId));
                                        models.setIndustryId(object1.getString(ConstantClass.industryId));
                                        second.add(object1.getString(ConstantClass.productTypeId));
                                        models.setProductType(object1.getString(ConstantClass.productType));
                                        first.add(object1.getString(ConstantClass.productType));
                                    }

                                }
                                expandableListDetailnew.put(industryname, first);
                                expandableListDetailnewsecond.put(industryname, second);
                                id1.add(object1.getString(ConstantClass.industryId));
                            }

                            System.out.println("name===" + object.getString(ConstantClass.industries_name) + "id====" + object.getString(ConstantClass.industriesid) + "parentid==" + object.getString(ConstantClass.parentId));
                            System.out.println("name new ===" + object1.getString(ConstantClass.industries_name) + "productid====" +object1.getString(ConstantClass.productTypeId) + "industryid==" + object1.getString(ConstantClass.industryId));

                        }
                        expandableListTitlenew = new ArrayList<String>(expandableListDetailnew.keySet());
                        expandableListid = new ArrayList<>(expandableListDetailnewsecond.keySet());
                        System.out.println("title==="+expandableListTitlenew+"details=="+expandableListDetailnew);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException io) {
                io.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            expandableListDetail=expandableListDetailnew;
            expandableListTitle=expandableListTitlenew;
            expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail,imagesworkplace);
            expandableListView.setAdapter(expandableListAdapter);

        }
    }

}

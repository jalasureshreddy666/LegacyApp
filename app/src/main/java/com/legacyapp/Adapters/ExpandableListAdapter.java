package com.legacyapp.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legacyapp.R;

import java.util.LinkedHashMap;
import java.util.List;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<String>> expandableListDetail;
    private int[] images;

    public ExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 LinkedHashMap<String, List<String>> expandableListDetail ,int[] images ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.images=images;

    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        Typeface typefacechild = Typeface.createFromAsset(context.getAssets(), "DIN-Light.otf");
        
        expandedListTextView.setTypeface(typefacechild);
        expandedListTextView.setText(expandedListText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {

        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
     //   View v= convertView.findViewById(R.id.view);
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        final ImageView imageView=(ImageView)convertView.findViewById(R.id.imageview);
        ImageView image=(ImageView)convertView.findViewById(R.id.image);

       // imageView.setImageResource(images[listPosition]);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Glide.with(context).load(images[listPosition]).crossFade().diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);

            }
        });
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "DIN-Light.otf");
        listTitleTextView.setTypeface(typeface);

        listTitleTextView.setText(listTitle.toUpperCase());

        if(isExpanded){
            image.setImageResource(R.drawable.arrow_icon_2);
            listTitleTextView.setBackgroundColor(context.getResources().getColor(R.color.white));
            Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "DIN-Medium.otf");
            listTitleTextView.setTextColor(context.getResources().getColor(R.color.black));
            listTitleTextView.setTypeface(typeface1);
        }
        else
        {
            listTitleTextView.setTextColor(context.getResources().getColor(R.color.white));
            image.setImageResource(R.drawable.arrow_icon_1);
            listTitleTextView.setBackgroundColor(context.getResources().getColor(R.color.background));
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}

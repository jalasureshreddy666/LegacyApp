package com.legacyapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legacyapp.Activities.ProductInfo;
import com.legacyapp.Models.RowItem;
import com.legacyapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;

    int position;
    View view2;
    boolean clicked=false;
    ViewHolder holder;
    int value=0;
    boolean nodata =false;
    private CustomPagerAdapter mAdapter;

    private List<RowItem> myList;  // for loading main list
    private ArrayList<RowItem> arraylist=null;

    public CustomListViewAdapter(Context context, int resourceId, List<RowItem> items) {
        super(context, resourceId, items);

        this.myList = items;
        this.arraylist = new ArrayList<RowItem>();
        arraylist.clear();
        this.arraylist.addAll(myList);

        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {

        private LinearLayout pager_indicator;
        private ViewPager viewPager;
        private RelativeLayout relativeLayout;
        private ImageView imageViewinfo;
        private TextView  textViewclicked;
        private TextView  textline;

        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;

        //    private RelativeLayout relativeLayoutclicked;
        //   private ImageView imageViewclicked;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

         holder = null;

        RowItem rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null || convertView.getTag() == null)
        {
            convertView = mInflater.inflate(R.layout.health_items ,null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
         //   holder.imageViewclicked=(ImageView) convertView.findViewById(R.id.clickedimage);
          //  holder.relativeLayoutclicked=(RelativeLayout) convertView.findViewById(R.id.clicked);
            holder.imageViewinfo=(ImageView) convertView.findViewById(R.id.info);
            holder.textViewclicked=(TextView) convertView.findViewById(R.id.titleclicked);
            holder.viewPager= (ViewPager) convertView.findViewById(R.id.viewpager);
            holder.textline=(TextView) convertView.findViewById(R.id.textline);

            holder.relativeLayout=(RelativeLayout) convertView.findViewById(R.id.viewPagerIndicator);
            holder.pager_indicator = (LinearLayout) convertView.findViewById(R.id.viewPagerCountDots);

            holder.txtTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "DIN-Light.otf"));
            holder.txtDesc.setTypeface(Typeface.createFromAsset(context.getAssets(), "DIN-Light.otf"));
            holder.txtDesc.setMovementMethod(new ScrollingMovementMethod());
            convertView.setTag(holder);
        } else
            {
            holder = (ViewHolder) convertView.getTag();
            }

        holder.txtDesc.setText(myList.get(position).getDesc());
        holder.txtTitle.setText(myList.get(position).getTitle());
        if(rowItem.getTitle()!="Product not available") {
            Glide.with(context).load(rowItem.getImageId())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);

        }
        else
        {
            Glide.with(context).load(R.mipmap.product)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
            nodata=true;
        }






      // holder.pager_indicator.invalidate();

        if(this.position == position & clicked == true & nodata == false )
        {

            mAdapter = new CustomPagerAdapter(context, myList.get(position).getImageId(),5);
            holder.viewPager.setAdapter(mAdapter);
            holder.viewPager.setCurrentItem(0);
            if(holder.pager_indicator!=null && holder.pager_indicator.getChildCount()>0 )
            {
                holder.pager_indicator.removeAllViews();
            }
            setPageViewIndicator();


            holder.txtTitle.setVisibility(View.GONE);
            holder.txtDesc.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.GONE);
          //  holder.relativeLayoutclicked.setVisibility(View.VISIBLE);

          //  Glide.with(context).load(rowItem.getImageId()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageViewclicked);
            holder.imageViewinfo.setVisibility(View.VISIBLE);
            holder.textViewclicked.setVisibility(View.VISIBLE);
            holder.textViewclicked.setText(rowItem.getTitle());
            holder.viewPager.setVisibility(View.VISIBLE);
            holder.textline.setVisibility(View.VISIBLE);
            holder.relativeLayout.setVisibility(View.VISIBLE);

            //holder.pager_indicator.setVisibility(View.VISIBLE);

            //view2 = mInflater.inflate(R.layout.list_itemnew, null);
           // ImageView imageView =(ImageView)view2.findViewById(R.id.imageview);
           // imageView.setImageResource(R.drawable.caf);
           // System.out.println("row value====="+rowItem.getImageId());
           // Picasso.with(context).load(rowItem.getImageId()).into(imageView);
          // Glide.with(context).load(rowItem.getImageId()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
           // TextView info = (TextView) view2.findViewById(R.id.info);

           // info.setText(news.getInfo());

            //return view2;
        }
        else
        {
            holder.txtTitle.setVisibility(View.VISIBLE);
            holder.txtDesc.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.VISIBLE);
       //     holder.relativeLayoutclicked.setVisibility(View.GONE);
            holder.imageViewinfo.setVisibility(View.GONE);
            holder.textViewclicked.setVisibility(View.GONE);
            holder.viewPager.setVisibility(View.GONE);
            holder.textline.setVisibility(View.GONE);
            holder.relativeLayout.setVisibility(View.GONE);
         //   holder.pager_indicator.setVisibility(View.GONE);
        }

      //  holder.imageView.setImageResource(rowItem.getImageId());
       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked===="+position);
                  Intent i = new Intent(context, Productview.class);
                *//*i.putExtra("key", HealthCareItems.productname.get(position));
                i.putExtra("PR-Desc", HealthCareItems.productdescription.get(position));
                i.putExtra("PR-Id",HealthCareItems.productid.get(position));
                i.putExtra("BitmapImage",HealthCareItems.productimage.get(position));
                  i.putExtra("PR-BENEFITS",HealthCareItems.brochure_file.get(position));
                i.putExtra("msds",HealthCareItems.msds_file.get(position));*//*

                i.putExtra("key", myList.get(position).getTitle());
                i.putExtra("PR-Desc", myList.get(position).getDesc());
                i.putExtra("PR-Id",myList.get(position).getImageId());
                i.putExtra("BitmapImage",myList.get(position).getProductimage());
                i.putExtra("PR-BENEFITS",myList.get(position).getBrochurefile());
                i.putExtra("msds",myList.get(position).getMsdsfile());

                // i.putExtra("img",images[position]);
                context.startActivity(i);
            }
        });*/
        holder.imageViewinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(context, ProductInfo.class);
                i.putExtra("title", myList.get(position).getTitle());
                i.putExtra("desc", myList.get(position).getDesc());
                i.putExtra("id",myList.get(position).getImageId());
                i.putExtra("image",myList.get(position).getProductimage());
                i.putExtra("benefits",myList.get(position).getBrochurefile());
                i.putExtra("msds",myList.get(position).getMsdsfile());
                context.startActivity(i);
            }
        });
        return convertView;
    }
    public void selectedItem(int position)
    {
        clicked=true;
        this.position = position; //position must be a global variable
    }

    private void setPageViewIndicator() {

        //  Log.d("###setPageViewIndicator", " : called");
        final int dotsCount = mAdapter.getCount();
        System.out.print("dots count"+dotsCount);
        final ImageView[] dots = new ImageView[dotsCount];
        System.out.print("dots count ==="+dots);

        for (int i = 0; i <dotsCount; i++) {
            dots[i] = new ImageView(context);
            dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    holder.viewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });

            holder.pager_indicator.addView(dots[i], params);
        }

        holder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i <dotsCount; i++) {
                    dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }
                dots[position].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
                if (position + 1 == dotsCount) {

                } else {

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        dots[0].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
    }


    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        myList.clear();
        clicked = false;
        if (charText.length() == 0) {
            myList.addAll(arraylist);
        }
        else
        {

            for (RowItem wp : arraylist) {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    myList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

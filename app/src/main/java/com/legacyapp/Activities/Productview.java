package com.legacyapp.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legacyapp.Adapters.CustomPagerAdapter;
import com.legacyapp.R;

public class Productview extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    ImageView productview, animview, videoview, /*product*/ product_info;
    TextView txt, details, spec;
    Intent b;
    String id, name, image, pro_desc, pro_benefits, msds;
    ImageView imageView,close,play;
    AlertDialog.Builder builder;
     AlertDialog dialog;
     int status;

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_3d);

     //   productview = (ImageView) findViewById(R.id.anim_3dview);
        videoview = (ImageView) findViewById(R.id.video_view);
        animview = (ImageView) findViewById(R.id.anim_2dview);




       // product = (ImageView) findViewById(R.id.picture);
        product_info = (ImageView) findViewById(R.id.info);
        //txt_id = (TextView)findViewById(R.id.text_id);
        txt = (TextView) findViewById(R.id.title);
        Typeface typefacechild = Typeface.createFromAsset(getApplicationContext().getAssets(), "DIN-Medium.otf");
        txt.setTypeface(typefacechild);

      /*  details = (TextView) findViewById(R.id.productspec);
        Typeface desc = Typeface.createFromAsset(getApplicationContext().getAssets(), "DIN-Light.otf");
        details.setTypeface(desc);

        spec = (TextView) findViewById(R.id.Spec);
        Typeface specc = Typeface.createFromAsset(getApplicationContext().getAssets(), "DIN-Bold.otf");
        spec.setTypeface(specc);*/



        b = getIntent();

        name = b.getStringExtra("key");
        id = b.getStringExtra("PR-Id");
        image = b.getStringExtra("BitmapImage");
        pro_desc = b.getStringExtra("PR-Desc");

        pro_benefits = b.getStringExtra("PR-BENEFITS");
        msds = b.getStringExtra("msds");

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(this, image,5);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);

        setPageViewIndicator();


        txt.setText(name);
        //txt_id.setText(id);

       /* Glide.with(Productview.this).load(image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(product);*/


        product_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Productview.this, ProductInfo.class);
                i.putExtra("title", name);
                i.putExtra("image", image);
                i.putExtra("id", id);
                i.putExtra("desc", pro_desc);
                i.putExtra("benefits", pro_benefits);
                i.putExtra("msds", msds);
                startActivity(i);

            }
        });

    /*    productview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* Intent i = new Intent(Productview.this, ProductMain.class);
                i.putExtra("bmp_Image", image);
                startActivity(i);*//*
            }
        });*/

    }

    private void setPageViewIndicator() {

        //  Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    public void video(View view) {

       /* AlertDialog.Builder builder = new  AlertDialog.Builder(this);
       final AlertDialog dialog =builder.create();*/
       status=1;
       dialog();

    }
    public void image2d(View view)
    {
       status=2;
        dialog();
    }
    public void dialog()
    {
        builder = new  AlertDialog.Builder(this);
        dialog =builder.create();
        LayoutInflater inflater =getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_layout, null);
        imageView = (ImageView) dialogLayout.findViewById(R.id.image);
        close=(ImageView) dialogLayout.findViewById(R.id.close);
        play=(ImageView)dialogLayout.findViewById(R.id.play);
        if(status==2)
        {
            play.setVisibility(View.INVISIBLE);
        }
        else
        {
            play.setVisibility(View.VISIBLE);
        }
        Glide.with(Productview.this).load(image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

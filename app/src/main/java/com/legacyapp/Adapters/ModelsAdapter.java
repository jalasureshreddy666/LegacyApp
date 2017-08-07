package com.legacyapp.Adapters;



import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legacyapp.Models.TopModels;
import com.legacyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelsAdapter extends RecyclerView.Adapter<ModelsAdapter.ViewHolder>  {
    private static ClickListener clickListener;
    private ArrayList<HashMap<String, String>> modelist = new ArrayList<>();
    private List<TopModels> modelsList;
    private Context context;
    private static int[] imgs = { R.drawable.health_new, R.drawable.food_new, R.drawable.workplace_new, R.drawable.hospitality_new, R.drawable.technology_new};
    private static String[] text = { "HEALTHCARE", "FOOD", "WORKPLACE", "HOSPITALITY", "TECHNOLOGY"};
    android.support.v4.app.FragmentManager manager;
    RecyclerView recyclerView;
   /* public ModelsAdapter(Context context, List<TopModels> Topmodels) {
        this.context = context;
        this.modelsList = Topmodels;
    }*/
    public ModelsAdapter(Context context) {
        this.context = context;
       // this.modelsList = Topmodels;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
       /* TextView tv, name;
        ImageView img, fav;*/
        TextView  name;
        ImageView img ;


        public ViewHolder(View rowView)
        {
            super(rowView);
         //   name = (TextView) rowView.findViewById(R.id.textview);
            name = (TextView) rowView.findViewById(R.id.textview);
            img = (ImageView) rowView.findViewById(R.id.imageview);
            rowView.setOnClickListener(this);
           /* name = (TextView) rowView.findViewById(R.id.product_name);
            tv = (TextView) rowView.findViewById(R.id.textView2);
            img = (ImageView) rowView.findViewById(R.id.imageView1);
            fav = (ImageView) rowView.findViewById(R.id.fav);*/
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ModelsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


    @Override
    public ModelsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_double, parent, false);

        ViewHolder  vh =new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ModelsAdapter.ViewHolder holder, final int position) {

      //  final TopModels models = modelsList.get(position);

       // holder.tv.setText(models.getproduct_id());
       // holder.name.setText(models.getIndustries_name());
        Glide.with(context).load(imgs[position])
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
       /* Glide.with(context).load(text[position])
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.name);*/
       holder.name.setText(text[position]);
      //  holder.tv.setTypeface(Typeface.createFromAsset(context.getAssets(), "DIN-Light.otf"));
        holder.name.setTypeface(Typeface.createFromAsset(context.getAssets(), "DIN-Medium.otf"));

       /* holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*FragmentManager fm = ((Activity)context).getFragmentManager();
                android.app.FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.pager, new Home(), "fragment_screen");
                ft.commit();*//*
               *//* Intent i = new Intent(context, Productview.class);
                System.out.println("POS-->" + modelsList.get(position).getproduct_name());
                i.putExtra("POS", position);
                i.putExtra("PR-Id", modelsList.get(position).getproduct_id());
                i.putExtra("key", modelsList.get(position).getproduct_name());
                i.putExtra("BitmapImage", modelsList.get(position).getproduct_image());
                i.putExtra("PR-Desc", modelsList.get(position).getProd_desc());
                i.putExtra("PR-BENEFITS", modelsList.get(position).getProd_benefits());
                i.putExtra("msds",modelsList.get(position).getMsds());
              //
                //  i.putExtra("PR-Benf", modelsList.get(position).getProd_benefits());
                context.startActivity(i);*//*
                // Intent i = new Intent(context, Productview.class);
                //Fragment f = new FoodService();

            }

        });*/

    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }
}

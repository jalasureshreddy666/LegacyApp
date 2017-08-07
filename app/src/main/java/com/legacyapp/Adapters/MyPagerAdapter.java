package com.legacyapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.legacyapp.Fragments.HealthCare;
import com.legacyapp.Fragments.Home;
import com.legacyapp.Fragments.WorkPlace;
import com.legacyapp.Fragments.Hospitality;
import com.legacyapp.Fragments.Technology;
import com.legacyapp.Fragments.FoodService;


public class MyPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public MyPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        context = ctx;
    }

    private Fragment f = null;

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                f = new Home();
                break;
            case 1:
                f = new WorkPlace();
                break;
            case 2:
                f = new Hospitality();
                break;
            case 3:
                f = new FoodService();
                break;
            case 4:
                f = new Technology();
                break;
            case 5:
                f = new HealthCare();
                break;

        }
        return f;
    }

    @Override
    public int getCount() { // Return the number of pages
        return 6;
    }

    // Set the tab text
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "TOP";
        }
        if (position == 1) {
            return "WORKPLACE";
        }
        if (position == 2) {
            return "HOSPITALITY";
        }
        if (position == 3) {
            return "FOOD SERVICE";
        }
        if (position == 4) {
            return "TECHNOLOGY";
        }
        if( position == 5) {
            return "HEALTHCARE";
        }
        return getPageTitle(position);
    }

}

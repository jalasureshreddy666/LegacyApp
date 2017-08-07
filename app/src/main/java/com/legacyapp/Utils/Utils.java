package com.legacyapp.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class Utils {

    public static boolean isSmsPermissionGranted(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) != PackageManager.GET_PERMISSIONS) {

            if (ContextCompat.checkSelfPermission(context, Manifest.permission_group.STORAGE) != PackageManager.GET_PERMISSIONS){
                return true;
            }

            if (ContextCompat.checkSelfPermission(context, Manifest.permission_group.CAMERA) != PackageManager.GET_PERMISSIONS)
           {
                return true;
            }

            else if (ContextCompat.checkSelfPermission(context, Manifest.permission.WAKE_LOCK) != PackageManager.GET_PERMISSIONS)
            {
                return true;
            }

            return true;
        } else {
            return false;
        }


    }

}

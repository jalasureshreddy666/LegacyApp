package com.legacyapp.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WAKE_LOCK;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Base activity for request Runtime Permissions
 */
public abstract class BaseActivity extends AppCompatActivity
{

    private final int MY_PERMISSIONS = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(checkPermission()){
            // Toast.makeText(this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
        }
        else {
            requestPermission();
        }

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]
                {
                        CAMERA,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                        WAKE_LOCK
                }, MY_PERMISSIONS);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MY_PERMISSIONS:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0)
                {
                    // setAppAsDefaultMessenger();
                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean write = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean wake = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    if (camera && read && write && wake)
                    {

                        Toast.makeText(this, "Permissions Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this,"Permissions Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  boolean checkPermission()
    {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int Fourth = ContextCompat.checkSelfPermission(getApplicationContext(), WAKE_LOCK);


        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                Fourth == PackageManager.PERMISSION_GRANTED;


    }

}

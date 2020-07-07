package com.io.my_book_store_track.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by inderjot
 */


public class PermissionFile {

    private static final int MULTI_LOC_STOR =45 ;
    private Context mContext;


    public PermissionFile(Context context) {
        mContext = context;
    }

    public boolean checkLocStorgePermission(Context ctx) {



        int readStorage = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeStorage = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int camera = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA);


        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readStorage != PackageManager.PERMISSION_GRANTED && writeStorage != PackageManager.PERMISSION_GRANTED && camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions((FragmentActivity) ctx,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTI_LOC_STOR);
                return false;
            }
        }

        return true;
    }

    public File getFile() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + "SampleApp"
                + File.separator);
    }

    public String getUniqueImageFilename() {
        return "img_" + System.currentTimeMillis() + ".png";
    }




}

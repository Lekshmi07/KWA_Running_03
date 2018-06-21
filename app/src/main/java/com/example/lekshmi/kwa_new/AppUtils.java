package com.example.lekshmi.kwa_new;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

public class AppUtils {

    private static final String TAG = "npkTest: AppUtils";

    static void makeCall(Context context) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "makeCall: Calling Phone activity");
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8943444242"));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Log.e(TAG, "makeCall: No permission");
            Toast.makeText(context, "No permission for Phone", Toast.LENGTH_SHORT).show();
        }
    }
}

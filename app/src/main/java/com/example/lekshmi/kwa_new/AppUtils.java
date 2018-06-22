package com.example.lekshmi.kwa_new;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.rtp.AudioGroup;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class AppUtils {

    private static final String TAG = "npkTest: AppUtils";
    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    static AudioGroup dtm=new AudioGroup();

    static void makeCall(Context context, String Num) {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "makeCall: Calling Phone activity");
            String Tel="Tel:"+Num;
            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("Tel:8943444242"));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


            String stateStr = i.getExtras().getString(TelephonyManager.EXTRA_STATE);
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                dtm.sendDtmf(1);
            }

        } else {
            Log.e(TAG, "makeCall: No permission");
            Toast.makeText(context, "No permission for Phone", Toast.LENGTH_SHORT).show();
        }
    }
}

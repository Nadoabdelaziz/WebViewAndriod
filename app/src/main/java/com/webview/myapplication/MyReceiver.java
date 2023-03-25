package com.webview.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /*intent contains the action that was triggered*/
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())){
//            Toast.makeText(context, "Screen Back", Toast.LENGTH_SHORT).show();
            Intent newintent = new Intent(context, MainActivity.class);
            context.startActivity(newintent);
        }
    }
}
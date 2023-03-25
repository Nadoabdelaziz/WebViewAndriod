package com.webview.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class    WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    WebAppInterface() {

    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }



    @JavascriptInterface
    public void notifyme(String alertmsg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext.getApplicationContext(), "My Notification");
        builder.setContentTitle("SafeChat");
        builder.setContentText("You Have New Unread Messages.");
        builder.setSmallIcon(R.mipmap.safechat);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.safechat));
        builder.setAutoCancel(true);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        // url app
//        Intent notificationIntent = new Intent(mContext, mContext.getClass());
//        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
//        Context context = mContext.getApplicationContext();

        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
                new Intent(mContext, mContext.getClass()), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        WebAppInterface instance = new WebAppInterface();
//        String pckgname = instance.getClass().getPackage().getName();
//        Uri alarmSound = Uri.parse("android.resource://"
//                + pckgname + "/" + R.raw.beep);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(mContext.getApplicationContext(), alarmSound);
//        builder.setOnlyAlertOnce(true);
//        r.play();


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(mContext.getApplicationContext());
        managerCompat.notify(1,builder.build());



        // banner alert
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;




        //
//        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(mContext);
//        alertbuilder.setTitle("Name");
//
//        LayoutInflater inflater= LayoutInflater.from(mContext);
//
//        // set the custom layout
//        final View customLayout = inflater.inflate(R.layout, null);
//        builder.setView(customLayout);
//
//        // add a button
//        builder.setPositiveButton("OK", (dialog, which) -> {
//            // send data from the AlertDialog to the Activity
//            EditText editText = customLayout.findViewById(R.id.editText);
//            sendDialogDataToActivity(editText.getText().toString());
//        });
//        // create and show the alert dialog
//        AlertDialog dialog = builder.create();
//        dialog.show();
        //


        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle("You Have New Messages!");
        alertDialog.setIcon(R.mipmap.safechat);
        alertDialog.setMessage("\n");
        alertDialog.setMessage(Html.fromHtml(alertmsg));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
//        alertDialog.getWindow().setLayout((6 * width)/9, ActionBar.LayoutParams.WRAP_CONTENT);
//        alertDialog.getWindow().setGravity(Gravity.TOP);


    }
}
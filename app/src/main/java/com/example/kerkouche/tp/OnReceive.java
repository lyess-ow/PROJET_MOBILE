package com.example.kerkouche.tp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class OnReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        if (intent.getAction().equalsIgnoreCase("com.example.Broadcast")){
            if (!bundle.getString("msg").equals("")){
                NewMessageNotification mynotify = new NewMessageNotification();
                mynotify.notify(context,bundle.getString("msg"),123);
            }
        }
    }
}

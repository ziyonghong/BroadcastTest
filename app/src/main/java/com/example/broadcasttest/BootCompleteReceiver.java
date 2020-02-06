package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
//在AndroidManifest.xml中将这个广播接收器的类名注册进去
public class BootCompleteReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,"boot complete",Toast.LENGTH_SHORT).show();
    }
}

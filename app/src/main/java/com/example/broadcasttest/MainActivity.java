package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//动态注册监听网络变化
public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private  NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter =new IntentFilter();
        intentFilter.addAction("android.net.comm.CONNECTIVITY_CHANGE"); //增加广播接收器想要监听的广播
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);  //注册
    }

    protected  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);  //取消注册
    }
   //内部类，当网络状态变化时，onReceive()方法得到执行
    class NetworkChangeReceiver extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent){
//            Toast.makeText(context,"network changes",Toast.LENGTH_SHORT).show();
//            Log.d("NetworkChangeReceiver","onReceive");

            //修改成提示用户当前网络是否可用
            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo(); //在AndroidManifest.xml 文件加入访问权限
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
}

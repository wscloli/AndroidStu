package com.example.a25492.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private OfflineReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.a25492.broadcast.OFFLINE");
        receiver=new OfflineReceiver();
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver !=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

     class OfflineReceiver extends BroadcastReceiver{
         @Override
         public void onReceive(final Context context, Intent intent) {
             AlertDialog.Builder builder=new AlertDialog.Builder(context);
             builder.setTitle("warning");
             builder.setMessage("你已断开连接，请尝试重新登录");
             builder.setCancelable(false);
             builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     ActivityCollector.finishAll();  //销毁所有活动
                     Intent intent1=new Intent(context,MainActivity.class);
                     context.startActivity(intent1);   //重启登陆界面的活动
                 }
             });
             builder.show();
         }
     }
}


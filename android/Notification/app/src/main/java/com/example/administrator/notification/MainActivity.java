package com.example.administrator.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*NotificationManager对通知进行管理
        * 调用Content的getSystemService（）方法获得 ，
        * 添加一个参数用来确定获取系统的是哪个服务  （Content.NOTIFICATION_SERVICE）
        *
         */
        Button send=(Button) findViewById(R.id.send_button);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.send_button:
                Intent intent=new Intent(this,Main2Activity.class);
                //PendingIntent简单理解为延迟执行的Intent  （可按需求选择使用的是getActivity(),getBroadcast(),getService()）
                //第一个参数context，第二个通常传入的0，第三个是Intent对象，，第四个通常传0
                PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //方法过时，new NotificationCompat.Builder(context,String channelId);
                Notification notification=new NotificationCompat.Builder(this,"default")
                        .setContentTitle("这是标题")    //指定通知的标题
                        .setContentText("这是正文内容")   //指定通知的正文内容
                        .setWhen(System.currentTimeMillis()) //指定通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)  //设置通知的小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //用来设置通知的大图标，下拉系统状态栏时，看到设置大图标
                        .setContentIntent(pi)  //
                        .setLights(Color.GREEN,1000,1000)  //设置通知的呼吸灯闪烁
                        .setVibrate(new long[]{0,1000,1000,1000})   //设置手机通知震动的 （记得声明权限）
                        .setAutoCancel(true)   //自动点击关闭通知

                        .build();
                manager.notify(1,notification);   //让通知显示出来，第一个是id （每个通知不同）
                //第二个是notification  ，上面刚创建好的那个
                break;
              default:
                    break;
        }
    }
}

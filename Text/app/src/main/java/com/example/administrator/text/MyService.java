package com.example.administrator.text;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    String TAG="MyService";

    private DownloadBinder mBinder=new DownloadBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
//创建新服务
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }
//启用服务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }
//销毁服务
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload");
        }
        public int getProgress(){
            Log.d(TAG, "getProgress");
            return 0;
        }
    }
}

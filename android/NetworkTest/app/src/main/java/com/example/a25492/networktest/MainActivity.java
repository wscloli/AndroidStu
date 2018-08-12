package com.example.a25492.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button send;
private TextView res;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button) findViewById(R.id.send);
        res=(TextView) findViewById(R.id.res);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send){
            send();
        }
    }

    private void send() {
        //开启线程来发起网络请求
        // runnable就像名字一样，是一个可运行对象，
        // 而thread是一个线程。单使用runnable是无法启动一个线程的。
        // 在多线程编程中需要大量使用thread类。
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;

            }
        }

        );
    }
}

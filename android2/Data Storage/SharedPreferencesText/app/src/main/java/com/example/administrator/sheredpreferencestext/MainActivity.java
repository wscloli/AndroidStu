package com.example.administrator.sheredpreferencestext;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData=(Button) findViewById(R.id.save_data);
        //保存数据
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得getSharedPrerences()方法
                //第一个参数是指定SharedPrerences文件的名称，第二个参数是用于指定操作模式。
               //保存  put
                SharedPreferences.Editor editor=getSharedPreferences(
                        "data",
                        MODE_PRIVATE).edit();
                editor.putString("name","Tony");
                editor.putInt("age",22);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });
        //读取保存的数据
        //读取  get
        Button restoreData=(Button)findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
            String name=pref.getString("name","");
            int age=pref.getInt("age",0);
            boolean married=pref.getBoolean("married",false);
                Toast.makeText(MainActivity.this,"name:"+name+"\tage:"+age+"\tmarried:"+married,Toast.LENGTH_LONG).show();
            }
        });
        //实现记住密码功能

    }
}

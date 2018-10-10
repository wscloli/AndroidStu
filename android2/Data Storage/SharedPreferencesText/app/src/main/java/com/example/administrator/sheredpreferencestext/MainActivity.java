package com.example.administrator.sheredpreferencestext;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData=(Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得getSharedPrerences()方法
                //第一个参数是指定SharedPrerences文件的名称，第二个参数是用于指定操作模式。
                SharedPreferences.Editor editor=getSharedPreferences(
                        "data",
                        MODE_PRIVATE).edit();
                editor.putString("name","Tony");
                editor.putInt("age",22);
                editor.putBoolean("merried",false);
                editor.apply();
            }
        });
    }
}

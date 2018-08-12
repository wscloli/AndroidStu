package com.example.a25492.databasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button create;
private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create=(Button) findViewById(R.id.create);
        //构建了一个MyDatabaseHelper对象，通过构造函数的参数将
        // 数据库名为BookStore.db
        //版本号为1
         dbHelper= new MyDatabaseHelper(this, "BookStore.db", null, 1);
         create.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dbHelper.getWritableDatabase();
             }
         });


    }
}

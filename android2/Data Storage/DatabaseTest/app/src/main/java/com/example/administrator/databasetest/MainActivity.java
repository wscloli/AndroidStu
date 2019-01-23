package com.example.administrator.databasetest;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private Button addButton;
    private Button updateButton;


    private Button createButton;
    private CheckBox saveCheck;
    private EditText accountEdit;
    private EditText passEdit;
    private MyDatabaseHelper dbHelper;
    LinearLayout linearLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login_item:
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                /*android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
//INVISIBLE 隐藏（位置可见）     GONE 隐藏（不可见）     VISIBLE 可见
                saveButton.setVisibility(View.GONE);
                transaction.replace(R.id.main_LinearLayout, new LoginFragment());
                //返回上一页
                transaction.addToBackStack(null);
                transaction.commit();*/
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.main_LinearLayout);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        //创建数据库
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
                //添加数据
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SQLiteOpenHelper 中的方法  创建和读取数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储类型，只能存基本类型，键值对
                ContentValues values = new ContentValues();
                //组装第一条数据
                values.put("name", "C++");
                values.put("author", "Wang");
                values.put("pages", 454);
                values.put("price", 80);
                //数据插入数据库
                db.insert("Book", null, values);
                //清除数据
                values.clear();
                //组装第二条数据
                values.put("name", "Java");
                values.put("author", "liu");
                values.put("pages", 1500);
                values.put("price", 90);
                db.insert("Book", null, values);
                values.clear();
            }
        });
//更新数据
        updateButton=(Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",30);
                db.update("Book",values,"name=?",new String[]{"C++"});
            }
        });

    }4
}

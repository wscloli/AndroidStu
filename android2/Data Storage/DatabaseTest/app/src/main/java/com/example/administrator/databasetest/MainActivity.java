package com.example.administrator.databasetest;

import android.app.FragmentManager;
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
private Button createButton;
private CheckBox saveCheck;
private EditText accountEdit;
private EditText passEdit;
private MyDatabaseHelper dbHelper;
    LinearLayout linearLayout;

    public MainActivity() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
//定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_item:
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();

                linearLayout.setVisibility(View.INVISIBLE);
                transaction.replace(R.id.main1_LinearLayout,new LoginFragment());
                //返回上一页
                transaction.addToBackStack(null);
                transaction.commit();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        saveButton=findViewById(R.id.saveButton);
        linearLayout=(LinearLayout) findViewById(R.id.main_LinearLayout);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });



    }
}

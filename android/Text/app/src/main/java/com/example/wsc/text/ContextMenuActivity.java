package com.example.wsc.text;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContextMenuActivity extends AppCompatActivity {
    //private String[] data={"文件一","文件二","文件三","文件四","文件五","文件六"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        showListView();

    }

    @Override//重写上下文菜单内容
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle( "文件操作");
        menu.setHeaderIcon(R.drawable.wsc);
        //代码动态添加（add）
        menu.add(1,1,1,"复制");
        menu.add(1,2,1,"剪切");
        menu.add(1,3,1,"粘贴");
        menu.add(1,4,1,"重命名");
        menu.add(1,5,1,"自定义");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(ContextMenuActivity.this,"这是复制",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);

    }

    private void showListView(){
        //设置listView显示内容
        ListView listView=(ListView)findViewById(R.id.list_View);//获取元素
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(ContextMenuActivity.this,android.R.layout.simple_list_item_1,getData());
        listView.setAdapter(adapter);
        this.registerForContextMenu(listView);//给View注册上下文菜单
    }
    private ArrayList<String>getData(){
    ArrayList<String>list=new ArrayList<String>();
        for (int i=0;i<5;i++) {
            list.add("文件" + (i+1));
        }
        return list;
        }

    }



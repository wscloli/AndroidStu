package com.example.wsc.text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubMenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //手动添加菜单
        /*SubMenu file=menu.addSubMenu("文件");
        SubMenu edit=menu.addSubMenu("编辑");//添加主菜单
        //添加子菜单
        file.add(1,1,1,"新建");
        file.add(1,2,1,"粘贴");
        file.add(1,3,1,"剪切");
        file.add(1,4,1,"重命名");

        //设置标题名
        file.setHeaderTitle("wsc");

        edit.add(2,1,1,"新建");
        edit.add(2,2,1,"粘贴");
        edit.add(2,3,1,"剪切");
        edit.add(2,4,1,"重命名");*/
        getMenuInflater().inflate(R.menu.submenu,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override//重写设置响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
       /* if (item.getGroupId() == 1) {
            switch (item.getItemId()) {
                case 1:
                    Toast.makeText(this,"这是复制1",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                case 3:
                case 4:

            }
        }else if (item.getGroupId() == 2) {
                switch (item.getItemId()) {
                    case 1:
                        Toast.makeText(this,"这是复制2",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                    case 3:
                    case 4:
                }

            }*/
       switch (item.getItemId()){
           case R.id.file_item1:
               Toast.makeText(this, "这是复制(xml)",Toast.LENGTH_SHORT).show();
               break;
           case R.id.file_item2:
               //后面懒得写，省略
       }

            return super.onOptionsItemSelected(item);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        //向下一活动传递数据
        //取出传递的数据
        /*Intent intent=getIntent();//获取用于启动活动的Intent
        String data=intent.getStringExtra("extra_data");//传入相应的键值，得到传递的数据
        Log.d("SubMenuActivity",data);*/
       //返回数据给上一活动
        Button button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("data_return","HELLO MIKU!");
                setResult(RESULT_OK,intent);//专门向上一活动返回数据的
                finish();//销毁当前活动的
                //会返回上一活动的onActivityResult()方法
                //要在上一活动重写此活动
            }
        });
    }

    /*@Override//当按Back键返回上一活动时，可以重写此方法
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("data_return","Hello MIKU");
        setResult(RESULT_OK,intent);
        finish();

    }*/
}

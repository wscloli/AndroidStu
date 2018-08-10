package com.example.wsc.text;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2,menu);
        //menu.add(1,100,1,"菜单一");     //动态添加菜单项   （groupId(分组),itemId（item名）,order（排序）,title（文本内容））
        return true;     //显示菜单
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){    //判断点击的是那一个菜单项
            case R.id.Test:
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                 //活动跳转
                item.setIntent(intent);
               // Toast.makeText(this,"测试1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Test2:
                Toast toast=Toast.makeText(this,"测试二",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button1=(Button)findViewById(R.id.button);
        //
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,ContextMenuActivity.class);
                startActivity(intent);
            }
        });
        //
        Button button2=(Button)findViewById(R.id.button6);//获取控件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,SubMenuActivity.class);
                startActivity(intent);
            }
        });

        //隐式intent
        Button button7=(Button) findViewById(R.id.button7);
        //设置点击响应
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开网页
                //Intent intent=new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.baidu.com"));
                //调出拨打电话界面
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));

                //地理位置定位
                //Intent intent=new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("geo:38.899533,-77.036476"));
                //卸载软件
                //Intent intent=new Intent(Intent.ACTION_DELETE);
                //intent.setData(Uri.fromParts("package","xxx",null));
                //<intent-filter> 中配置的<data> 的使用
                startActivity(intent);
            }
        });
    //向下一活动传递数据
         Button button8=(Button) findViewById(R.id.button8);//获取控件
         button8.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //String data="Hello miku!";//要传递的数据
                 Intent intent=new Intent(Main2Activity.this, SubMenuActivity.class);
                 //intent.putExtra("extra_data",data);//传递数据
                 //startActivity(intent);
                 startActivityForResult(intent,1);//启动活动后可以返回一个结果给上一个活动
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //检测requestCode的值判断数据来源

        switch(requestCode) {
            case 1:
               if(resultCode==RESULT_OK){
                   String returnedData=data.getStringExtra("data_return");
                   Log.d("Main2Activity",returnedData);
               }
               break;
            default:
        }

    }
}

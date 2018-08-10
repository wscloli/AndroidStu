package com.example.wsc.text;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //log t+Tab键
    private static final String TAG = "MainActivity";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"ADD",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"REMOVE",Toast.LENGTH_SHORT).show();
                break;
            case R.id.intent_item:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                // item.setIntent(intent); 跳转活动
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//加载一个布局
        //调试信息  Log.d()   日志
        Log.d("MainActivity","onCreate execute");

        Log.w(TAG, "onCreate: " );
        /*
        *Toast*/
        Button button1=(Button)findViewById(R.id.button);  //获取布局文件定义元素
        button1.setOnClickListener(new View.OnClickListener(){//注册一个监听器
            public void onClick(View v){//执行监听器中的onclick方法
                Toast.makeText(MainActivity.this,"www",Toast.LENGTH_SHORT).show();
            }

        });

        //带有图片的toast
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast toast=Toast.makeText(MainActivity.this,"图片Toast",Toast.LENGTH_SHORT);
                LinearLayout toast_layout=(LinearLayout)toast.getView();
                ImageView iv=new ImageView(MainActivity.this);
                iv.setImageResource(R.drawable.wsc);
                toast_layout.addView(iv,0);
                toast.show();
            }
        });

        //自定义的toast
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                View toast_view=inflater.inflate(R.layout.activity_main2,null); //引用的这个活动里的对象
                Toast toast=new Toast(MainActivity.this);
                toast.setView(toast_view);
                toast.show();
            }
        });



        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"销毁活动",Toast.LENGTH_SHORT).show();
                //销毁活动~
               // finish();
            }
        });


        //使用Intent切换活动

        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //显式intent
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                //隐式intent
                //Intent intent=new Intent("com.example.activitytest.ACTION_START");
                //intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });


    }
}

package com.example.a25492.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private TextView username;
private  TextView userpass;
private EditText passText;
private EditText nameText;
private Button login;
private CheckBox save;
private SharedPreferences pref;
private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(TextView) findViewById(R.id.name);
        userpass=(TextView)findViewById(R.id.pass);
        nameText=(EditText)findViewById(R.id.nameText);
        passText=(EditText)findViewById(R.id.passText);
        login=(Button) findViewById(R.id.login);
        save=(CheckBox)findViewById(R.id.save) ;
        pref = getSharedPreferences("userinfo",MODE_PRIVATE );    //使用Shared
        editor=pref.edit();    //编辑器对象
        String name=pref.getString("nameText","");  //获取
        if(name==null){
            save.setChecked(false);
        }else{
            save.setChecked(true);
            nameText.setText(name);   //给值
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString().trim();   //trim()  去空格
                String pass=passText.getText().toString().trim();
                //Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                if(name.equals("admin") && pass.equals("123456")){
                   if(save.isChecked()){
                       editor.putString("nameText",name);    //条件都符合就存储数据
                       editor.commit();
                   }
                else{
                    editor.remove("nameText");
                    editor.commit();
                    }
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

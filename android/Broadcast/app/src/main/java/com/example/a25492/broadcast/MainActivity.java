package com.example.a25492.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText accoutEdit;
private EditText passwordEdit;
private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accoutEdit=(EditText)findViewById(R.id.editname);
        passwordEdit=(EditText)findViewById(R.id.editpassword);
        login=(Button)findViewById(R.id.buttonlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accoutEdit.getText().toString();   //获取用户名控件里的文本内容
                String password=passwordEdit.getText().toString();  //获取密码控件里的文本内容
                //如果账户名不是admin，密码不是123456，则登陆失败
                if(account.equals("admin") && password.equals("123456")){
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"密码错误或用户名不存在",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

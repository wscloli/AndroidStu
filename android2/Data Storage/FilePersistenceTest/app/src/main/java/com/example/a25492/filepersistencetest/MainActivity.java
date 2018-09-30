package com.example.a25492.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.edit);
String inputText=load();
//字符串的非空判断，一次进行两种空值的判断
if(!TextUtils.isEmpty(inputText)){
    editText.setText(inputText);
    editText.setSelection(inputText.length());//将输入光标移动到文本末尾，方便继续输入
    Toast.makeText(this,"读取了："+inputText,Toast.LENGTH_LONG).show();
}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText=editText.getText().toString();
        save(inputText);
    }
//save 方法把输入的内容存储到文件中，文件命名为data
    private void save(String inputText) {
        FileOutputStream out=null;
        BufferedWriter writer=null;
        //使用OutputStreamWriter构建出一个BUfferedWriter对象，
        // 通过BufferedWriter来将文本内容写入文件中。
        try {
            out=openFileOutput("data", Context.MODE_APPEND);
            //MODE_PRIVATE   如果有内容将会覆盖   MODE_APPEND   如果有内容将会追加
            writer=new BufferedWriter(new OutputStreamWriter(out));//字符流转字节流
            writer.write(inputText);//写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();//关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public String load(){
        FileInputStream in=null;
        BufferedReader read=null;
    StringBuilder content=new StringBuilder();
        try {
            in=openFileInput("data");  //读取文件
            read=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=read.readLine())!=null){  //读取到的数据不为空
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                read.close();  //关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content.toString();
    }
}

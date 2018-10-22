package com.example.administrator.databasetext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
var dbHelper: MyDatabaseHelper? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
dbHelper= MyDatabaseHelper(this,"BookStore.db",null,1)
        saveButton.setOnClickListener {
            //调用getWritableDatabase()
            dbHelper?.writableDatabase
        }
    }
}
//然后使用adb shell来对数据库和表的创建进行检查
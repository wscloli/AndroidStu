package com.example.administrator.databasetext

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper : SQLiteOpenHelper {
    //建表（数据库）
    val CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    var mContext: Context? = null
        //初始化
    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version) {
        mContext = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //执行建表语句
    db?.execSQL(CREATE_BOOK)
        Toast.makeText(mContext,"创建成功",Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}


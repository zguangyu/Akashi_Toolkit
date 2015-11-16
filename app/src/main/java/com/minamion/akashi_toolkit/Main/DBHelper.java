package com.minamion.akashi_toolkit.Main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // 数据库名称
    public static final String DBNAME = "crius.db";
    // 数据库版本
    public static final int VERSION = 2;
    public DBHelper(Context c, String dbName) {
        super(c, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO 创建数据库后，创建表
        db.execSQL("CREATE TABLE mytable (_savedata INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, value REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO 更改数据库版本的操作
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
// TODO 每次成功打开数据库后首先被执行
    }
}

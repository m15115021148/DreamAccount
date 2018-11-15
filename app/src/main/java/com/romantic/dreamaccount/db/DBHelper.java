package com.romantic.dreamaccount.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1; //版本号
    private static DBHelper instance = null;
    //app数据库名称
    private static final String DB_NAME = "account.db";

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public synchronized  static DBHelper getNewInstanceDBHelper(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
            if (db.getVersion()<DATABASE_VERSION){
                db.setVersion(DATABASE_VERSION);
            }
        }
        return instance;
    }

    //---------------------表名---------------------
    /**
     * 表名
     */
    protected static final String ACCOUNT = "accounts";

    //创建数据表
    private static final String CREATE_ACCOUNT_TABLE = "create table "
            + ACCOUNT
            + " (id integer primary key autoincrement,type integer,time varchar2(20),money varchar2(20),kind varchar2(20),note varchar2(100),lat varchar2(10),lng varchar2(10),address varchar2(100))";

    /**
     *创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("result", "创建数据库成功--------------->>开始--");
        //创建收藏表
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    /**
     *版本更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 删除所有表
     *
     */
    public void clearTable() {
        db.execSQL("delete from " + ACCOUNT + ";");
    }
}

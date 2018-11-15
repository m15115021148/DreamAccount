package com.romantic.dreamaccount.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.romantic.dreamaccount.bean.AccountsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenM} on 2018/11/15.
 */
public class DBAccount {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TYPE = "type";
    public static final String MONEY = "money";
    public static final String KIND = "kind";
    public static final String NOTE = "note";
    public static final String TIME = "time";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String ADDRESS = "address";
    public static final String ID = "id";

    public DBAccount(Context context) {
        dbHelper = DBHelper.getNewInstanceDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    /**
     * 插入数据
     */
    public void insert(AccountsBean model) {
        ContentValues values = new ContentValues();
        values.put("type", model.getType());
        values.put("money", model.getMoney());
        values.put("kind", model.getKind());
        values.put("note", model.getNote());
        values.put("time", model.getTime());
        values.put("lat", model.getLat());
        values.put("lng", model.getLng());
        values.put("address", model.getAddress());
        db.insert(DBHelper.ACCOUNT, null, values);
        Log.i("result", "插入成功");
    }

    /**
     * 删除表
     */
    public void delTab() {
        db.execSQL("delete from " + DBHelper.ACCOUNT + ";");
    }

    /**
     * 删除一条记录
     *
     * @param id
     */
    public void delOneAccount(int id) {
        String sql = "delete from " + DBHelper.ACCOUNT + " where id=" + id + ";";
        Log.e("result", "sql:" + sql);
        db.execSQL(sql);
    }

    /**
     * 查询数据
     * @return
     */
    public List<AccountsBean> getData() {
        List<AccountsBean> list = new ArrayList<>();

        String sql = "select * from " + DBHelper.ACCOUNT;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            int type = cursor.getInt(cursor.getColumnIndex(TYPE));
            String time = cursor.getString(cursor.getColumnIndex(TIME));
            String money = cursor.getString(cursor.getColumnIndex(MONEY));
            String kind = cursor.getString(cursor.getColumnIndex(KIND));
            String note = cursor.getString(cursor.getColumnIndex(NOTE));
            String lat = cursor.getString(cursor.getColumnIndex(LAT));
            String lng = cursor.getString(cursor.getColumnIndex(LNG));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));

            AccountsBean bean = new AccountsBean();
            bean.setId(id);
            bean.setType(type);
            bean.setTime(time);
            bean.setMoney(Double.parseDouble(money));
            bean.setKind(kind);
            bean.setNote(note);
            bean.setLat(Double.parseDouble(lat));
            bean.setLng(Double.parseDouble(lng));
            bean.setAddress(address);

            list.add(bean);
        }


        return list;
    }


}

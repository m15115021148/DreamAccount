package com.sensology.framelib.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.sensology.framelib.XConfigure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SharedPref implements ICache {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    static final String SP_NAME = XConfigure.CACHE_SP_NAME;

    private static SharedPref instance;

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPref getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPref.class) {
                if (instance == null) {
                    instance = new SharedPref(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    @Override
    public void remove(String key) {
        editor.remove(key);
        editor.apply();
    }


    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void clear() {
        editor.clear().apply();
    }


    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void putLong(String key, Long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }


    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }


    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void put(String key, Object value) {

    }

    /**
     * 将Object信息Base64后存入Preferences
     *
     * @param name
     * @param obj
     */

    public <T> void setPreferences(String name, T obj) {
        if (obj == null) {
            sharedPreferences.edit().putString(name, null).apply();
            return;
        }
        ByteArrayOutputStream toByte = null;
        ObjectOutputStream oos = null;
        try {
            toByte = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(toByte);
            oos.writeObject(obj);
            // 对byte[]进行Base64编码
            String obj64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));
            sharedPreferences.edit().putString(name, obj64).apply();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (toByte != null) toByte.close();
                if (oos != null) oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取SharePreference中值，Base64解码之后传出
     *
     * @param name
     */
    @SuppressWarnings("unchecked")
    public <T> T getPreferences(String name, T obj) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            String obj64 = sharedPreferences.getString(name, "");
            if (TextUtils.isEmpty(obj64)) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(obj64, Base64.DEFAULT);
            bais = new ByteArrayInputStream(base64Bytes);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) bais.close();
                if (ois != null) ois.close();
            } catch (Exception e) {

            }
        }
        return null;
    }
}

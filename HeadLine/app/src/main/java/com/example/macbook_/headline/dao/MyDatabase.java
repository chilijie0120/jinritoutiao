package com.example.macbook_.headline.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.macbook_.headline.bean.Home_Dtail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/16.
 */
public class MyDatabase {
    private MySqliteOpenHelper helper;
    private SQLiteDatabase db;
    private List<Home_Dtail> list=new ArrayList<>();
    public MyDatabase(Context context) {
        helper=new MySqliteOpenHelper(context);
        db=helper.getWritableDatabase();
    }
    public void addGuanzhu(String image,String title,String source,String url){
        if (isExist(title))
        return;
        //String table, String nullColumnHack, ContentValues values
        ContentValues values=new ContentValues();
        values.put("image",image);
        values.put("title",title);
        values.put("source",source);
        values.put("url",url);
       db.insert("guanzhu",null,values);
    }
    public List<Home_Dtail> queryGuanzhu(){
        //String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy
        Cursor cursor = db.query("guanzhu",new String[]{"image","title", "source","url"}, null, null, null, null, null);
        while (cursor.moveToNext()){
            String image=cursor.getString(cursor.getColumnIndex("image"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String source=cursor.getString(cursor.getColumnIndex("source"));
            String url=cursor.getString(cursor.getColumnIndex("url"));
            list.add(new Home_Dtail(title,source,url,new Home_Dtail.User_info(image)));
        }
        return list;
    }
    public boolean isExist(String title){
        Cursor rawQuery = db.rawQuery("select * from guanzhu where title=?",new String[]{title});
       if (rawQuery.moveToNext())
            return true;
        return false;
    }
}

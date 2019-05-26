package com.example.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME="MYDB";
    public static String TABLE_NAME="notes";
    public static String COL1 = "ID";
    public static String COL2 = "DAY";
    public static String COL3 = "NOTE";

    public DBHelper(Context context) {
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, DAY TEXT, NOTE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //create a method for inseting data into table
    public boolean insert(String day,String note){
        //get reference to SQLite DB
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,day);
        contentValues.put(COL3,note);

        Long res = db.insert(TABLE_NAME,null,contentValues);

        if(res == -1) return false;
        else
            return true;
    }

    public Cursor getAllData(){
        //get reference to SQLite DB
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);

        return cursor;
    }

    public boolean delData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();

        int res = db.delete(TABLE_NAME, "ID="+Integer.parseInt(ID),null);
        //one method
        //db.execSQL("delete from "+TABLE_NAME+" where ID = "+(Integer.parseInt(ID)));
        if(res>0){
        return true;
        }else
            return false;
    }

}

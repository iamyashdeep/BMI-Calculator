package com.example.yashdeepsingh.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yashdeep Singh on 02-10-2016.
 */

public class Sqlite extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "BMI_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";



    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (Username String PRIMARY KEY ,Height Float ,Weight Float ,BMI Float)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public boolean insertData(String name,Float height,Float weight, Float bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",name);
        contentValues.put("Height",height);
        contentValues.put("Weight",weight);
        contentValues.put("BMI",bmi);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String name,Float height,Float weight, Float bmi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",name);
        contentValues.put("Height",height);
        contentValues.put("Weight",weight);
        contentValues.put("BMI",bmi);
        long result = db.update(TABLE_NAME,contentValues,"Username = ?",new String[]{name});
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor point = db.rawQuery("select Username, Height, Weight, BMI  from "+TABLE_NAME+" where Username = '"+name+"'",null);
        return point;
    }

    public int deleteData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Username = ?",new String[] {name});

    }







}


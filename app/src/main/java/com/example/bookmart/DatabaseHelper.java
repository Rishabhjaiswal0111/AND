package com.example.bookmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String REGISTER = "register";
    public static final String F_NAME = "F_name";
    public static final String L_NAME = "L_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, REGISTER + ".db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_quer= "create table " + REGISTER + " (Id integer primary key autoincrement," + F_NAME + " text," + L_NAME + " text," + EMAIL + " text," + PASSWORD + " text)";
        String create_add_book="create table add_book (id integer primary key autoincrement ,title varchar(50), discription varchar(500) ,image blob,contact int(10),price int(5),location text)";
        sqLiteDatabase.execSQL(create_add_book);
        sqLiteDatabase.execSQL(create_quer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add_new(String F_name,String L_name,String Email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(F_NAME,F_name);
        cv.put(L_NAME,L_name);
        cv.put(EMAIL,Email);
        cv.put(PASSWORD,password);
        long insert = db.insert(REGISTER, null, cv);
        if(insert==-1){
            return false;
        }
        else {
            return true;
        }


    }

    public boolean check_login(String email,String password){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id FROM REGISTER  WHERE email=? AND password=?",new String[]{email,password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            cursor.close();
            return true;
        }
        return false;


    }

    public boolean check_register(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM REGISTER WHERE TRIM(email) = '"+email.trim()+"'", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            cursor.close();
            return true;
        }
        return false;
    }
}


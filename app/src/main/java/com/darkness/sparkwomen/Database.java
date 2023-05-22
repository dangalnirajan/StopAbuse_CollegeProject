//package com.darkness.sparkwomen;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class Database extends SQLiteOpenHelper {
//    public static final String databaseName ="stopAbuseSignup.db";
//
//    public Database(@Nullable Context context) {
//        super(context, "stopAbuseSignup.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase MyDatabase) {
//        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
//        MyDatabase.execSQL("drop Table if exists allusers");
//
//    }
//
//    public boolean insertData(String email, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email",email);
//        contentValues.put("password",password);
//        long result= MyDatabase.insert("allusers",null,contentValues);
//
//        if (result==-1){
//            return false;
//        }else {
//            return true;
//        }
//    }
//    public boolean checkEmail(String email){
//        SQLiteDatabase MyDabase = this.getWritableDatabase();
//        Cursor cursor = MyDabase.rawQuery("Select * from allusers where email =? ", new String[]{email});
//        if (cursor.getCount()>0){
//            return true;
//        }else {
//            return false;
//        }
//    }
//    public boolean checkEmailPassword(String email, String password){
//        SQLiteDatabase MyDabase = this.getWritableDatabase();
//        Cursor cursor = MyDabase.rawQuery("Select * from allusers where email =? and password =? ", new String[]{email,password});
//        if (cursor.getCount()>0){
//            return true;
//        }else {
//            return false;
//        }
//    }
//}

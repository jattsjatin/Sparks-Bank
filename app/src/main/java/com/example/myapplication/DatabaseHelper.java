package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(6207886695,'Jatin singh',12630000.00,'jatinsingh10357@gmail.com','XXXXXXXXXXXX4321','js23676541')");
        db.execSQL("insert into user_table values(6235678901,'Anmol',264231.67,'anumalik@gmail.com','XXXXXXXXXXXX5682','Anm99654438')");
        db.execSQL("insert into user_table values(9846789012,'amit',56624.56,'amit@gmail.com','XXXXXXXXXXXX8917','Ami876554525')");
        db.execSQL("insert into user_table values(8841890123,'vikash',986300.01,'vikash@gmail.com','XXXXXXXXXXXX4784','Vik65443213')");
        db.execSQL("insert into user_table values(8978901234,'Aman',7803.48,'aman@gmail.com','XXXXXXXXXXXX2389','Amn45432108')");
        db.execSQL("insert into user_table values(8584012345,'Rohit',56945.16,'rohit@gmail.com','XXXXXXXXXXXX2559','Roh59321099')");
        db.execSQL("insert into user_table values(9890175556,'Yogesh',25036.00,'yogesh@gmail.com','XXXXXXXXXXXX5565','Yog54210984')");
        db.execSQL("insert into user_table values(8912347567,'akash',98657.22,'akash@gmail.com','XXXXXXXXXXXX5289','Aka54632109879')");
        db.execSQL("insert into user_table values(9632345678,'sudhansh',5994700.46,'suddfks@gmail.com','XXXXXXXXXXXX3758','Sud697898766')");
        db.execSQL("insert into user_table values(8964567809,'Rock',44700.00,'rock@gmail.com','XXXXXXXXXXXX7860','Roc56887650')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}

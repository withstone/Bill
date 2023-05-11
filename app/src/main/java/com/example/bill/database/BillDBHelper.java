package com.example.bill.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bill.entity.BillInfo;

import java.util.ArrayList;
import java.util.List;

public class BillDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Bill.db";
    private static final String TABLE_BILL_INFO="bill_info";
    private static final int DB_VERSION=1;
    private static BillDBHelper mHelper=null;
    private SQLiteDatabase mRDB=null;
    private SQLiteDatabase mWDB=null;

    public BillDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // 利用单例模式获取数据库帮助器的唯一实例
    public static BillDBHelper getInstance(Context context){
        if(mHelper==null){
            mHelper=new BillDBHelper(context);
        }
        return mHelper;
    }

    //打开数据库读写连接
    public SQLiteDatabase openReadLink(){
        if(mRDB==null || !mRDB.isOpen()){
            mRDB=mHelper.getReadableDatabase();
        }
        return mRDB;
    }
    public SQLiteDatabase openWriteLink(){
        if(mWDB==null || !mWDB.isOpen()){
            mWDB=mHelper.getWritableDatabase();
        }
        return mWDB;
    }
    //关闭数据库读写连接
    public void closeLink(){
        if(mRDB!=null && mRDB.isOpen()){
            mRDB.close();
            mRDB=null;
        }
        if(mWDB!=null && mWDB.isOpen()){
            mWDB.close();
            mWDB=null;
        }
    }


    // 创建数据库，执行建表语句
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建信息表
        String sql ="CREATE TABLE IF NOT EXISTS " + TABLE_BILL_INFO +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " date VARCHAR NOT NULL," +
                " type INTEGER NOT NULL," +
                " amount DOUBLE NOT NULL," +
                " remark VARCHAR NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    //保存一条记录
    public long save(BillInfo bill){
        ContentValues cv = new ContentValues();
        cv.put("date",bill.date);
        cv.put("type",bill.type);
        cv.put("amount",bill.amount);
        cv.put("remark",bill.remark);
        return mWDB.insert(TABLE_BILL_INFO,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @SuppressLint("Range")
    public List<BillInfo> queryByMonth(String yearMonth){
        List<BillInfo> list = new ArrayList<>();
        String sql = "select * from "+TABLE_BILL_INFO+" where date like '"+yearMonth+"%';";
        Log.d("xy",sql);
        Cursor cursor = mRDB.rawQuery(sql, null);
        while (cursor.moveToNext()){
            BillInfo bill = new BillInfo();
            bill.id= cursor.getInt(cursor.getColumnIndex("_id"));
            bill.date= cursor.getString(cursor.getColumnIndex("date"));
            bill.type= cursor.getInt(cursor.getColumnIndex("type"));
            bill.amount= cursor.getDouble(cursor.getColumnIndex("amount"));
            bill.remark= cursor.getString(cursor.getColumnIndex("remark"));
            list.add(bill);
        }


        return list;
    }
}

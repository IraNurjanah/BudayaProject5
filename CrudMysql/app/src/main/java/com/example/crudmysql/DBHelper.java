package com.example.crudmysql;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context){
        super(context, "AndroMobile", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "onCreate table mahasiswa(stb text(11) primary key, nama text(50), angkaan text(4))";
        SQLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

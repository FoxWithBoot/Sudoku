package com.example.kur;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    SQLHelper(Context context, int ver){
        super(context, Commands.DB_NAME, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Commands.CREATE_TABLE1);
        db.execSQL(Commands.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

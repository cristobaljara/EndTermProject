package com.cris.ukulele.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by crisp_000 on 05-03-2018.
 */

public class UkeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DATABASE_NAME = "ukuleleapp.db";

    public UkeBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + UkeDbSchema.UkeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_ID + ", " +
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_ARTIST + "," +
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_TITLE + ", " +
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_TABS + ", " +
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_CREATION +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

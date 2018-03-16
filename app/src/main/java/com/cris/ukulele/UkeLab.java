package com.cris.ukulele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cris.ukulele.database.UkeBaseHelper;
import com.cris.ukulele.database.UkeCursorWrapper;
import com.cris.ukulele.database.UkeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.cris.ukulele.database.UkeDbSchema.UkeTable.Cols;

/**
 * Created by crisp_000 on 05-03-2018.
 */

public class UkeLab {

    private static UkeLab sUkeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static UkeLab get(Context context) {

        if (sUkeLab == null) {
            sUkeLab = new UkeLab(context);
        }
        return sUkeLab;
    }

    private UkeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UkeBaseHelper(mContext).getWritableDatabase();
    }

    public void addSong(UkuleleData data) {
        ContentValues values = getContentValues(data);
        mDatabase.insert(UkeDbSchema.UkeTable.NAME, null, values);
    }

    public List<UkuleleData> getAllSongs() {

        List<UkuleleData> songs = new ArrayList<>();
        UkeCursorWrapper cursor = querySongs(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                songs.add(cursor.getSong());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return songs;
    }

    public UkuleleData getSong(UUID id) {

        UkeCursorWrapper cursor = querySongs(
                UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        try {

            if (cursor.getCount() == 0) {

                return null;
            }
            cursor.moveToFirst();
            return cursor.getSong();
        } finally {
            cursor.close();
        }
    }

    public void updateSong(UkuleleData data) {
        UUID id = data.getId();
        ContentValues values = getContentValues(data);
        mDatabase.update(UkeDbSchema.UkeTable.NAME, values,
                Cols.UKULELE_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});

    }

    private UkeCursorWrapper querySongs(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UkeDbSchema.UkeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UkeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(UkuleleData data) {
        ContentValues values = new ContentValues();

        values.put(Cols.UKULELE_COLUMN_ID, data.getId().toString());
        values.put(Cols.UKULELE_COLUMN_ARTIST, data.getArtist());
        values.put(Cols.UKULELE_COLUMN_TITLE, data.getTitle());
        values.put(Cols.UKULELE_COLUMN_TABS, data.getTabs());
        values.put(Cols.UKULELE_COLUMN_CREATION, data.getCreationDate().getTime());

        return values;
    }


    public static void addSong() {
    }
}

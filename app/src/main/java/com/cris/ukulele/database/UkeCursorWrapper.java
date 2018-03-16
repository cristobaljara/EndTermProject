package com.cris.ukulele.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.cris.ukulele.UkuleleData;

import java.util.Date;
import java.util.UUID;

/**
 * Created by crisp_000 on 05-03-2018.
 */

public class UkeCursorWrapper extends CursorWrapper {
    public UkeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public UkuleleData getSong(){

        String uuidString = getString(getColumnIndex(UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_ID));
        String artist = getString(getColumnIndex(UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_ARTIST));
        String title = getString(getColumnIndex(UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_TITLE));
        String tabs =  getString(getColumnIndex(UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_TABS));
        long creationDate = getLong(getColumnIndex(UkeDbSchema.UkeTable.Cols.UKULELE_COLUMN_CREATION));

        UkuleleData data = new UkuleleData(UUID.fromString(uuidString));
//        data.setId();
        data.setArtist(artist);
        data.setTitle(title);
        data.setTabs(tabs);
        data.setCreationDate(creationDate);

        return data;
    }
}

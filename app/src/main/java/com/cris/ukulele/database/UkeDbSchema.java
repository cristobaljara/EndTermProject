package com.cris.ukulele.database;

/**
 * Created by crisp_000 on 05-03-2018.
 */

public class UkeDbSchema {
    public static final class UkeTable {
        public static final String NAME = "ukeTable";

        public static final class Cols {
            public static final String UKULELE_COLUMN_ID = "id";
            public static final String UKULELE_COLUMN_ARTIST = "artist";
            public static final String UKULELE_COLUMN_TITLE = "title";
            public static final String UKULELE_COLUMN_TABS = "tabs";
            public static final String UKULELE_COLUMN_CREATION = "creationDate";
        }
    }
}
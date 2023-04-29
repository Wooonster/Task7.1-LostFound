package com.example.lostfound.dbSettings;

import android.provider.BaseColumns;

public final class LostFoundContract {
    private LostFoundContract() {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
    }

    public static class PostEntry implements BaseColumns {

        public static final String TABLE_NAME = "post_table";
        public static final String POST_ID = "post_id";
        // true->lost false->found
        public static final String POST_TYPE = "post_type";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
        public static final String LOCATION = "location";
    }

}

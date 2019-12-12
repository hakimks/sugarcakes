package com.kawesi.sugarcakes.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.PortUnreachableException;

public final class CakeContract {
    private CakeContract(){}

    public static final String CONTENT_AUTHORITY = "com.kawesi.sugarcakes";


    // create a base Uri for all the Uris that the app will use to contact the content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    //path to the tables in the content provider
    public static final String PATH_CAKE = "cake-path";

    public static final class CakeEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CAKE);

        // The Mime Type for the list of cakes
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAKE;

        // The Mime Type for a single cake
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAKE;

        // name of databse table for cakes
        public static final String CAKE_TABLE = "cakes";

        public static final String _ID = BaseColumns._ID;

        // other column names
        public static final String CAKE_NAME = "cakename";
        public static final String CAKE_INGREDIENTS =  "cakeingredients";
        public static final String CAKE_PRICE = "cakeprice";
        public static final String CAKE_CATEGORY = "cakecategory";
        public static final String DATE_CREATED = "datecreated";

    }
}



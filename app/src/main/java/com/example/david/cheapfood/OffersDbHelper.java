package com.example.david.cheapfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andre on 16.05.2017.
 */

public class OffersDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = OffersDbHelper.class.getSimpleName();

    public static final String DB_NAME = "offer.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_OFFER_LIST = "offer_list";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CONTINGENT = "contingent";
    public static final String COLUMN_ADDRESS = "address";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_OFFER_LIST +
            "(" +   COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_PRICE + " REAL NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_CONTINGENT + " INTEGER NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL " +
            ");";

    public OffersDbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "Database: " + getDatabaseName() + " created.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d(LOG_TAG, "SQL: " + SQL_CREATE);
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Failed to create table: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

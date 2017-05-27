package com.example.david.cheapfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andre on 16.05.2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = DbHelper.class.getSimpleName();

    public static final String DB_NAME = "CheapFood.db";
    public static final int DB_VERSION = 1;

    public static final String OFFER_TABLE = "offer_table";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CONTINGENT = "contingent";
    public static final String COLUMN_ADDRESS = "address";

    public static final String SQL_CREATE_OFFER_TABLE =
            "CREATE TABLE " + OFFER_TABLE +
            "(" +   COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_PRICE + " REAL NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                    COLUMN_CONTINGENT + " INTEGER NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL " +
            ");";

    public static final String PURCHASEHISTORY_TABLE = "purchaseHistory_table";
    
    public static final String COLUMN_CUSTOMERID = "customerId";
    public static final String COLUMN_OFFERNAME = "offerName";
    public static final String COLUMN_OFFERPRICE = "offerPrice";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ORDERDATE = "orderDate";

    public static final String SQL_CREATE_PURCHASEHISTORY_TABLE =
            "CREATE TABLE " + PURCHASEHISTORY_TABLE +
                    "(" +   COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CUSTOMERID + " INTEGER NOT NULL, " +
                    COLUMN_OFFERNAME + " TEXT NOT NULL, " +
                    COLUMN_OFFERPRICE + " REAL NOT NULL, " +
                    COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                    COLUMN_ORDERDATE + " TEXT NOT NULL " +
                    ");";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d(LOG_TAG, "SQL: " + SQL_CREATE_OFFER_TABLE);
            db.execSQL(SQL_CREATE_OFFER_TABLE);
            db.execSQL(SQL_CREATE_PURCHASEHISTORY_TABLE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Failed to create tables: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

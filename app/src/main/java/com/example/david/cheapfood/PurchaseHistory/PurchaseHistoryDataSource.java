package com.example.david.cheapfood.PurchaseHistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.david.cheapfood.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by andre on 16.05.2017.
 */

public class PurchaseHistoryDataSource {

    private static final String LOG_TAG = PurchaseHistoryDataSource.class.getSimpleName();
    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.GERMANY);

    private SQLiteDatabase database;
    private DbHelper dbHelper;

    private String[] columns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_CUSTOMERID,
            DbHelper.COLUMN_OFFERNAME,
            DbHelper.COLUMN_OFFERPRICE,
            DbHelper.COLUMN_QUANTITY,
            DbHelper.COLUMN_ORDERDATE,
    };

    public PurchaseHistoryDataSource(Context context) {
        Log.d(LOG_TAG, "Create dbHelper.");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Received database reference | Path: " + database.getPath());
    }

    public void close(){
        dbHelper.close();
    }

    public PurchaseHistory createPurchaseHistory(PurchaseHistory purchaseHistory) {

        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_CUSTOMERID, purchaseHistory.getCustomerId());
        values.put(DbHelper.COLUMN_OFFERNAME, purchaseHistory.getOfferName());
        values.put(DbHelper.COLUMN_OFFERPRICE, purchaseHistory.getOfferPrice());
        values.put(DbHelper.COLUMN_QUANTITY, purchaseHistory.getQuantity());
        values.put(DbHelper.COLUMN_ORDERDATE, purchaseHistory.getOrderDate().toString());

        long insertId = database.insert(DbHelper.PURCHASEHISTORY_TABLE, null, values);

        Cursor cursor = database.query(DbHelper.PURCHASEHISTORY_TABLE,
                columns, DbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        PurchaseHistory dbPurchaseHistory = cursorToPurchaseHistory(cursor);
        cursor.close();

        return dbPurchaseHistory;
    }

    private PurchaseHistory cursorToPurchaseHistory(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_ID);
        int idCustomerId = cursor.getColumnIndex(DbHelper.COLUMN_CUSTOMERID);
        int idOfferName = cursor.getColumnIndex(DbHelper.COLUMN_OFFERNAME);
        int idOfferPrice = cursor.getColumnIndex(DbHelper.COLUMN_OFFERPRICE);
        int idQuantity = cursor.getColumnIndex(DbHelper.COLUMN_QUANTITY);
        int idOrderDate = cursor.getColumnIndex(DbHelper.COLUMN_ORDERDATE);

        long id = cursor.getLong(idIndex);
        long customerId = cursor.getLong(idCustomerId);
        String offerName = cursor.getString(idOfferName);
        double offerPrice = cursor.getDouble(idOfferPrice);
        long quantity = cursor.getLong(idQuantity);

        Date orderDate = null;
        try {
            orderDate = parser.parse(cursor.getString(idOrderDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new PurchaseHistory(id, customerId, offerName, offerPrice, quantity, orderDate);
    }

    public List<PurchaseHistory> getAllPurchaseHistories() {
        List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.PURCHASEHISTORY_TABLE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        PurchaseHistory purchaseHistory;

        while(!cursor.isAfterLast()) {
            purchaseHistory = cursorToPurchaseHistory(cursor);
            purchaseHistoryList.add(purchaseHistory);
            Log.d(LOG_TAG, "PurchaseHistory: " + purchaseHistory.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return purchaseHistoryList;
    }
}
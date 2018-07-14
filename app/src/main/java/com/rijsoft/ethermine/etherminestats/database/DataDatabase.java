package com.rijsoft.ethermine.etherminestats.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Constants;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

import java.util.Date;

public class DataDatabase extends SQLiteOpenHelper {
    private static final String TAG = "DB_log";

    public DataDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Log.d(TAG, Constants.DATABASE.CREATE_TABLE_CURRENT_STATS);
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_CURRENT_STATS);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Log.d(TAG, Constants.DATABASE.DROP_CURRENT_STATS);
            sqLiteDatabase.execSQL(Constants.DATABASE.DROP_CURRENT_STATS);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    public void cleatDataBase() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(Constants.DATABASE.DROP_CURRENT_STATS);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    public void insertIntoCurrentStats() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursorData = null;
       // cursorData = db.rawQuery(Constants.DATABASE.GET_DATAS_QUERY_ORDER_CARD+orderCard.getData().getId()+"' ", null);
      //  Log.d("addDataOrderCard", Constants.DATABASE.GET_DATAS_QUERY_ORDER_CARD+orderCard.getData().getId()+"' ");
        //запись есть - обновляем
  /*      if (cursorData.getCount() > 0) {
            db.execSQL(Constants.DATABASE.DELETE_DATAS_ORDER_CARD+orderCard.getData().getId()+ "'");
            Log.d("addDataOrderCard_cur", cursorData.getString(cursorData.getColumnIndex(Constants.DATABASE.ID_GUID_ORDER_CARD)));

        }*/
        //записи нет - создаем
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_JSON_BODY, "dfhdafghdszfgh");
        Date d = new Date();
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_TIME_LIFE, d.toString());

        try {
            Log.d(TAG, Constants.DATABASE.TABLE_NAME_CURRENT_STATS);
            db.insert(Constants.DATABASE.TABLE_NAME_CURRENT_STATS, null, valuesDatas);
        } catch (Exception e) {
            Log.d(TAG, e.fillInStackTrace().toString());
        }
    }


    public void getCurrentStatsFromDataBase(OverviewContract.GetDashboardIntractor.OnFinishedListener onFinishedListener) {
        CurrentStatsFetcher fetcher = new CurrentStatsFetcher(onFinishedListener, this.getWritableDatabase());
        fetcher.start();
    }

}

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
import com.rijsoft.ethermine.etherminestats.database.currenStats.CurrentStatsFetcher;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

import org.apache.commons.lang3.time.DateUtils;

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

    public void clearDataBase() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(Constants.DATABASE.DELETE_CURRENT_STATS);
        } catch (SQLException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    public void insertIntoCurrentStats(CurrentStats currentStats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_JSON_BODY, currentStats.toString());
        int addMinuteTime = 1;
        Date targetTime = new Date(); //now
        targetTime = DateUtils.addMinutes(targetTime, addMinuteTime); //add minute
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_TIME_LIFE, targetTime.toString());

        try {
            Log.d(TAG, currentStats.toString());
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

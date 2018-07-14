package com.rijsoft.ethermine.etherminestats.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.rijsoft.ethermine.etherminestats.Constants;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

public class CurrentStatsFetcher extends Thread {
    private OverviewContract.GetDashboardIntractor.OnFinishedListener onFinishedListener;
    private SQLiteDatabase mDb;

    public CurrentStatsFetcher(OverviewContract.GetDashboardIntractor.OnFinishedListener
                                       onFinishedListener,
                               SQLiteDatabase db) {
        this.onFinishedListener = onFinishedListener;
        mDb = db;
    }

    @Override
    public void run() {
        Cursor cursorData = mDb.rawQuery(Constants.DATABASE.GET_CURRENT_STATS, null);

        if (cursorData.getCount() > 0) {

            if (cursorData.moveToFirst()) {
                do {
                    Gson gson = new Gson();
                    // Convert JSON to Java Object
                  //  orderCard = gson.fromJson(cursorData.getString(cursorData.getColumnIndex(Constants.DATABASE.JSON_ORDER_CARD)), OrderCard.class);

                    // Convert JSON to JsonElement, and later to String
                    CurrentStats currentStats = new CurrentStats();
                    currentStats.setStatus(cursorData.getString(cursorData.getColumnIndex(Constants.DATABASE.CURRENT_STATS_JSON_BODY)));
                    publishData(currentStats);

                } while (cursorData.moveToNext());
            }
        }


    }
    public void publishData(final CurrentStats data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(data);
            }
        });
    }

}

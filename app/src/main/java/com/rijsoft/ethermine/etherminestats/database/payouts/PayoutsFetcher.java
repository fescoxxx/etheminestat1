package com.rijsoft.ethermine.etherminestats.database.payouts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.rijsoft.ethermine.etherminestats.Constants;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.contracts.PayoutsContract;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;

public class PayoutsFetcher extends Thread {
    private PayoutsContract.GetPayoutsIntractor.OnFinishedListener onFinishedListener;
    private SQLiteDatabase mDb;
    public PayoutsFetcher(PayoutsContract.GetPayoutsIntractor.OnFinishedListener
                                       onFinishedListener,
                               SQLiteDatabase db) {
        this.onFinishedListener = onFinishedListener;
        this.mDb = db;
    }
    @Override
    public void run() {
        Cursor cursorData = mDb.rawQuery(Constants.DATABASE.GET_PAYOUTS, null);
        if (cursorData.getCount() > 0) {
            if (cursorData.moveToFirst()) {
                do {
                    Gson gson = new Gson();
                    // Convert JSON to Java Object
                    // Convert JSON to JsonElement, and later to String
                    Payouts payouts = gson.fromJson(
                            cursorData.getString(
                                    cursorData.getColumnIndex(
                                            Constants.DATABASE.PAYOUTS_JSON_BODY)),
                            Payouts.class);
                    publishData(payouts);
                } while (cursorData.moveToNext());
            }
        }
    }

    public void publishData(final Payouts data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(data);
            }
        });
    }

}

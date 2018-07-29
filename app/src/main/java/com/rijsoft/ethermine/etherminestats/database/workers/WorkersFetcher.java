package com.rijsoft.ethermine.etherminestats.database.workers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.rijsoft.ethermine.etherminestats.Constants;
import com.rijsoft.ethermine.etherminestats.contracts.WorkersContract;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

public class WorkersFetcher extends Thread {
    private WorkersContract.GetWorkersIntractor.OnFinishedListener onFinishedListener;
    private SQLiteDatabase mDb;

    public WorkersFetcher(WorkersContract.GetWorkersIntractor.OnFinishedListener onFinishedListener,
                          SQLiteDatabase db) {
        this.onFinishedListener = onFinishedListener;
        this.mDb = db;
    }

    @Override
    public void run() {
        Cursor cursorData = mDb.rawQuery(Constants.DATABASE.GET_WORKERS, null);
        if (cursorData.getCount() > 0) {
            if (cursorData.moveToFirst()) {
                do {
                    Gson gson = new Gson();
                    // Convert JSON to Java Object
                    // Convert JSON to JsonElement, and later to String
                    Workers workers = gson.fromJson(
                            cursorData.getString(
                                    cursorData.getColumnIndex(
                                            Constants.DATABASE.WORKERS_JSON_BODY)),
                            Workers.class);
                    publishData(workers);
                } while (cursorData.moveToNext());
            }
        }
        if (mDb != null) {
            mDb.close();
        }

    }

    public void publishData(final Workers data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(data);
            }
        });
    }
}

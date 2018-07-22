package com.rijsoft.ethermine.etherminestats.database.currenStats;

import android.os.AsyncTask;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

public class CurrentStatsSaveIntoDatabase extends AsyncTask<CurrentStats, Void, Void> {
    private final String TAG = CurrentStatsSaveIntoDatabase.class.getSimpleName();
    private DataDatabase mDatabase;

    public CurrentStatsSaveIntoDatabase(DataDatabase mDatabase) {
        this.mDatabase  = mDatabase;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(CurrentStats... params) {
        CurrentStats currentStats = params[0];
        try {
            mDatabase.insertIntoCurrentStats(currentStats);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            mDatabase.close();
        }
        return null;
    }
}

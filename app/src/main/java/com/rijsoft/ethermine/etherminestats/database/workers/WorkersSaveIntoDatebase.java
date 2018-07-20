package com.rijsoft.ethermine.etherminestats.database.workers;

import android.os.AsyncTask;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

public class WorkersSaveIntoDatebase extends AsyncTask<Workers, Void, Void> {
    private final String TAG = WorkersSaveIntoDatebase.class.getSimpleName();
    private DataDatabase mDatabase;

    public WorkersSaveIntoDatebase(DataDatabase mDatabase) {
        this.mDatabase  = mDatabase;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Workers... params) {
        Workers workers = params[0];
        try {
            mDatabase.insertIntoWorkers(workers);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        return null;
    }
}

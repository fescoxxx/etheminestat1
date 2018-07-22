package com.rijsoft.ethermine.etherminestats.database.payouts;

import android.os.AsyncTask;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;

public class PayoutsSaveIntoDatabase extends AsyncTask<Payouts, Void, Void> {
    private final String TAG = PayoutsSaveIntoDatabase.class.getSimpleName();
    private DataDatabase mDatabase;

    public PayoutsSaveIntoDatabase(DataDatabase mDatabase) {
        this.mDatabase  = mDatabase;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Payouts... params) {
        Payouts payouts = params[0];
        try {
            mDatabase.insertIntoPayouts(payouts);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            mDatabase.close();
        }
        return null;
    }
}

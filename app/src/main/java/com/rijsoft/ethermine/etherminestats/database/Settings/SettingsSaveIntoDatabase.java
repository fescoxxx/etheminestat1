package com.rijsoft.ethermine.etherminestats.database.Settings;

import android.os.AsyncTask;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;

public class SettingsSaveIntoDatabase extends AsyncTask<Settings, Void, Void> {
    private final String TAG = SettingsSaveIntoDatabase.class.getSimpleName();
    private DataDatabase mDatabase;

    public SettingsSaveIntoDatabase(DataDatabase mDatabase){
        this.mDatabase  = mDatabase;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Settings... settings) {
        Settings setting = settings[0];
        try {
            mDatabase.insertIntoSettings(setting);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            mDatabase.close();
        }
        return null;
    }
}

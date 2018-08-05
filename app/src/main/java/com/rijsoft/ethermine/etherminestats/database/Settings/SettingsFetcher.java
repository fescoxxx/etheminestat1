package com.rijsoft.ethermine.etherminestats.database.Settings;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.rijsoft.ethermine.etherminestats.Constants;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;

public class SettingsFetcher extends Thread  {

    private SettingsContract.GetSettingsIntractor.OnFinishedListener onFinishedListener;
    private SQLiteDatabase mDb;
    private String tagAction;
    public SettingsFetcher(SettingsContract.GetSettingsIntractor.OnFinishedListener onFinishedListener,
                           SQLiteDatabase db,
                           String tagAction){
        this.onFinishedListener = onFinishedListener;
        this.mDb = db;
        this.tagAction = tagAction;
    }

    @Override
    public void run() {
        Cursor cursorData = mDb.rawQuery(Constants.DATABASE.GET_SETTINGS, null);
        if (cursorData.getCount() > 0) {
            if (cursorData.moveToFirst()) {
                do {
                    Gson gson = new Gson();
                    // Convert JSON to Java Object
                    // Convert JSON to JsonElement, and later to String
                    Settings settings = gson.fromJson(
                            cursorData.getString(
                                    cursorData.getColumnIndex(
                                            Constants.DATABASE.SETTINGS_JSON_BODY)),
                            Settings.class);
                    publishData(settings);
                } while (cursorData.moveToNext());
            }
        }
        if (mDb != null) {
            mDb.close();
        }
    }

    public void publishData(final Settings data) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(data, tagAction);
            }
        });
    }
}

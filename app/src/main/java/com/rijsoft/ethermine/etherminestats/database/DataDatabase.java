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
import com.rijsoft.ethermine.etherminestats.contracts.PayoutsContract;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.contracts.WorkersContract;
import com.rijsoft.ethermine.etherminestats.database.Settings.SettingsFetcher;
import com.rijsoft.ethermine.etherminestats.database.currenStats.CurrentStatsFetcher;
import com.rijsoft.ethermine.etherminestats.database.payouts.PayoutsFetcher;
import com.rijsoft.ethermine.etherminestats.database.workers.WorkersFetcher;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

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
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_PAYOUTS);
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_SETTINGS);
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_WORKERS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Log.d(TAG, Constants.DATABASE.DROP_CURRENT_STATS);
            sqLiteDatabase.execSQL(Constants.DATABASE.DROP_CURRENT_STATS);
            sqLiteDatabase.execSQL(Constants.DATABASE.DROP_PAYOUTS);
            sqLiteDatabase.execSQL(Constants.DATABASE.DROP_SETTINGS);
            sqLiteDatabase.execSQL(Constants.DATABASE.DROP_WORKERS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    //отчистка таблицы текущей статистики
    public void clearCurrentStats() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(Constants.DATABASE.DELETE_CURRENT_STATS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //очистка таблицы с выплатами
    public void clearPayouts() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(Constants.DATABASE.DELETE_PAYOUTS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //очистка таблицы с настройками
    public void clearSettings() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(Constants.DATABASE.DELETE_SETTINGS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //очистка таблицы с настройками
    public void clearWorkers() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(Constants.DATABASE.DELETE_WORKERS);
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //Вствака данных в БД текущей статистики
    public void insertIntoCurrentStats(CurrentStats currentStats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_JSON_BODY, currentStats.toString());
        valuesDatas.put(Constants.DATABASE.CURRENT_STATS_TIME_LIFE, getAddMinuteDate().toString());
        try {
            db.insert(Constants.DATABASE.TABLE_NAME_CURRENT_STATS, null, valuesDatas);
        } catch (Exception e) {
            Log.d(TAG, e.fillInStackTrace().toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //Вставка данных в БД выплаты
    public void insertIntoPayouts(Payouts payouts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.PAYOUTS_JSON_BODY, payouts.toString());
        valuesDatas.put(Constants.DATABASE.PAYOUTS_TIME_LIFE,getAddMinuteDate().toString());
        try {
            Log.d(TAG, payouts.toString());
            db.insert(Constants.DATABASE.TABLE_NAME_PAYOUTS, null, valuesDatas);
        } catch (Exception e) {
            Log.d(TAG, e.fillInStackTrace().toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //Вставка данных в БД Workers
    public void insertIntoWorkers(Workers workers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.WORKERS_JSON_BODY, workers.toString());
        valuesDatas.put(Constants.DATABASE.WORKERS_TIME_LIFE,getAddMinuteDate().toString());
        try {
            Log.d(TAG, workers.toString());
            db.insert(Constants.DATABASE.TABLE_NAME_WORKERS, null, valuesDatas);
        } catch (Exception e) {
            Log.d(TAG, e.fillInStackTrace().toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //Вставка данных в БД Settings
    public void insertIntoSettings(Settings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesDatas = new ContentValues();
        valuesDatas.put(Constants.DATABASE.SETTINGS_JSON_BODY, settings.toString());
        valuesDatas.put(Constants.DATABASE.SETTINGS_TIME_LIFE,getAddMinuteDate().toString());
        try {
            Log.d(TAG, settings.toString());
            db.insert(Constants.DATABASE.TABLE_NAME_SETTINGS, null, valuesDatas);
        } catch (Exception e) {
            Log.d(TAG, e.fillInStackTrace().toString());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //Получение данных из БД о Settings
    public void getSettingsFromDaraBase(SettingsContract.GetSettingsIntractor.OnFinishedListener onFinishedListener, String tagAction) {
        SettingsFetcher fetcher = new SettingsFetcher(onFinishedListener,this.getWritableDatabase(), tagAction);
        fetcher.start();
    }


    //получение данных из БД o Workers
    public void getWorkersFromDataBase(WorkersContract.GetWorkersIntractor.OnFinishedListener onFinishedListener){
        WorkersFetcher fetcher = new WorkersFetcher(onFinishedListener,this.getWritableDatabase());
        fetcher.start();
    }

    //получение данных из БД о выплатах
    public void getPayoutsFromDataBase(PayoutsContract.GetPayoutsIntractor.OnFinishedListener onFinishedListener){
        PayoutsFetcher fetcher = new PayoutsFetcher(onFinishedListener, this.getWritableDatabase());
        fetcher.start();
    }

    //получение данных из БД о текущей статистики
    public void getCurrentStatsFromDataBase(OverviewContract.GetDashboardIntractor.OnFinishedListener onFinishedListener) {
        CurrentStatsFetcher fetcher = new CurrentStatsFetcher(onFinishedListener, this.getWritableDatabase());
        fetcher.start();
    }

    private Date getAddMinuteDate() {
        int addMinuteTime = 1;
        Date targetTime = new Date(); //now
        targetTime = DateUtils.addMinutes(targetTime, addMinuteTime); //add minute
        return targetTime;
    }

}

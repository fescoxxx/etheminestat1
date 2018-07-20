package com.rijsoft.ethermine.etherminestats;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Preferences {

    public final static String FILE_NAME = "preferences";

    public final static String LIFE_TIME_OVERVIEW = "lifetime_overview";
    public final static String LIFE_TIME_PAYOUTS = "lifetime_pay";
    public final static String LIFE_TIME_WORKERS = "life_time_workers";

    public final static String MINER = "miner";


    private SharedPreferences preferences;
    private Context context;

    public Preferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, 0);

    }
    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    //Workers----------------------------------------------------------------------------------START
    public void setLifeTimeWorkers(String data) {
        getEditor().putString(LIFE_TIME_WORKERS, data).commit();
    }
    public String getLifeTimeWorkers() {
        return preferences.getString(LIFE_TIME_WORKERS, "");
    }
    //Workers------------------------------------------------------------------------------------END

    //Overview---------------------------------------------------------------------------------START
    public void setLifeTimeOverview(String data) {
        getEditor().putString(LIFE_TIME_OVERVIEW, data).commit();
    }
    public String getLifeTimeOverview() {
        return preferences.getString(LIFE_TIME_OVERVIEW, "");
    }
    //Overview-----------------------------------------------------------------------------------END

    //Payouts----------------------------------------------------------------------------------START
    public void setLifeTimePayouts(String data) {
        getEditor().putString(LIFE_TIME_PAYOUTS, data).commit();
    }
    public String getLifeTimePayouts() {
        return preferences.getString(LIFE_TIME_PAYOUTS, "");
    }
    //Payouts------------------------------------------------------------------------------------END

    public void setMiner(String miner) {
        getEditor().putString(MINER, miner).commit();
    }
    public String getMiner() {
        return preferences.getString(MINER, "");
    }

    public void updateDateLife(String tag) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        int addMinuteTime = 1;
        Date targetTime = new Date(); //now
        targetTime = DateUtils.addMinutes(targetTime, addMinuteTime); //add minute
        if (tag.equals(LIFE_TIME_OVERVIEW)) {
            setLifeTimeOverview(dateFormat.format(targetTime));
        } else if (tag.equals(LIFE_TIME_PAYOUTS)) {
            setLifeTimePayouts(dateFormat.format(targetTime));
        } else if (tag.equals(LIFE_TIME_WORKERS)) {
            setLifeTimeWorkers(dateFormat.format(targetTime));
        }
    }
}

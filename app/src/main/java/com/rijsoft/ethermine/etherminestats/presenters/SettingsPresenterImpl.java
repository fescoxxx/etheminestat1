package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsPresenterImpl implements SettingsContract.presenter, SettingsContract.GetSettingsIntractor.OnFinishedListener {


    private SettingsContract.MainView mainView;
    private SettingsContract.GetSettingsIntractor getSettingsIntractor;
    private Context context;
    private DataDatabase mDatabase;

    public SettingsPresenterImpl(SettingsContract.MainView mainView,
                                 SettingsContract.GetSettingsIntractor getSettingsIntractor,
                                 Context context) {
        this.mainView = mainView;
        this.getSettingsIntractor = getSettingsIntractor;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(mainView != null){
            mainView.showProgress();
        }
        getSettingsIntractor.getSettings(this, mDatabase, context);
    }

    @Override
    public void requestDataFromServer() {
        mDatabase = new DataDatabase(context) ;
        Date dateLife = new Date();
        Preferences preferences = new Preferences(context);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            dateLife = dateFormat.parse(preferences.getLifeTimePayouts());
            Log.d("preferences_0", dateLife.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("preferences", preferences.getLifeTimePayouts());

        if (dateLife.before(new Date())) {
            getSettingsIntractor.getSettings(this, mDatabase, context);
        } else  {
            mDatabase.getSettingsFromDaraBase(this);
        }
    }

    @Override
    public void onFinished(Settings settings) {
        if(mainView != null){
            mainView.setDataToShow(settings);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}

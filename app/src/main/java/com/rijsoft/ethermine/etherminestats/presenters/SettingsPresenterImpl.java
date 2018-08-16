package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.Utils;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.rijsoft.ethermine.etherminestats.Preferences.LIFE_TIME_SETTINGS;

public class SettingsPresenterImpl implements SettingsContract.presenter, SettingsContract.GetSettingsIntractor.OnFinishedListener {


    private SettingsContract.MainView mainView;
    private SettingsContract.GetSettingsIntractor getSettingsIntractor;
    private Context context;
    private DataDatabase mDatabase;
    private Preferences preferences;
    private String tagAction;

    public SettingsPresenterImpl(SettingsContract.MainView mainView,
                                 SettingsContract.GetSettingsIntractor getSettingsIntractor,
                                 Context context) {
        this.mainView = mainView;
        this.getSettingsIntractor = getSettingsIntractor;
        this.context = context;
        this.preferences = new Preferences(context);
        tagAction = context.getString(R.string.LOADFRAGMENT);

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

            if(Utils.isNetworkAvailable(context)) {
                getSettingsIntractor.getSettings(this, mDatabase, context,tagAction);
            }
            else {
                this.onFailure(new Throwable("No connection to internet"));
                mDatabase.getSettingsFromDaraBase(this, tagAction);
            }

    }

    @Override
    public void onClickOk(View view, String wallet) {
        if(view.getId() == R.id.buttonOk)
        preferences.setMiner(wallet);
        tagAction = context.getString(R.string.BTNCLICK);
        requestDataFromServer();
        preferences.setLifeTimePayouts(null);
        preferences.setLifeTimeWorkers(null);
        preferences.setLifeTimeOverview(null);
        Log.d("onClickOk", "onClickOk");
       // mainView.onClickResponce();
    }

    @Override
    public void requestDataFromServer() {
        mainView.showProgress();
        mDatabase = new DataDatabase(context) ;
        Date dateLife = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        if (preferences.getMiner().equals("")){
            this.onFailure(new Throwable("Enter your wallet"));
        } else {
            try {
                dateLife = dateFormat.parse(preferences.getLifeTimeSettings());
                Log.d("preferences_0", dateLife.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("preferences", preferences.getLifeTimeSettings());
            if (dateLife.before(new Date())) {
                if(Utils.isNetworkAvailable(context)) {
                    getSettingsIntractor.getSettings(this, mDatabase, context, tagAction);
                }
                else {
                    this.onFailure(new Throwable("No connection to internet"));
                    mDatabase.getSettingsFromDaraBase(this, tagAction);
                }
            } else  {
                mDatabase.getSettingsFromDaraBase(this, tagAction);
            }
        }
    }

    @Override
    public void onFinished(Settings settings, String tagAction) {
        if(mainView != null){
            mainView.setDataToShow(settings, tagAction);
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

package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.Utils;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OverviewPresenterImpl implements
        OverviewContract.presenter,
        OverviewContract.GetDashboardIntractor.OnFinishedListener{

    private OverviewContract.MainView mainView;
    private OverviewContract.GetDashboardIntractor getDashboardIntractor;
    private Context context;
    private DataDatabase mDatabase;

    public OverviewPresenterImpl(OverviewContract.MainView mainView,
                                 OverviewContract.GetDashboardIntractor getDashboardIntractor,
                                 Context context) {
        this.mainView = mainView;
        this.getDashboardIntractor = getDashboardIntractor;
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
            if(Utils.isNetworkAvailable(context)) {
                getDashboardIntractor.getOverview(this, mDatabase, context);
            } else {
                this.onFailure(new Throwable("No connection to internet"));
                mDatabase.getCurrentStatsFromDataBase(this);
            }

    }

    @Override
    public void requestDataFromServer() {
        mDatabase = new DataDatabase(context) ;
        Date dateLife = new Date();
        Preferences preferences = new Preferences(context);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            try {
                dateLife = dateFormat.parse(preferences.getLifeTimeOverview());
                Log.d("preferences_0", dateLife.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("preferences", preferences.getLifeTimeOverview());

            if (dateLife.before(new Date())) {
                if(Utils.isNetworkAvailable(context)) {
                    getDashboardIntractor.getOverview(this, mDatabase, context);
                } else {
                    this.onFailure(new Throwable("No connection to internet"));
                    mDatabase.getCurrentStatsFromDataBase(this);
                }
            } else  {
                mDatabase.getCurrentStatsFromDataBase(this);
            }

    }

    @Override
    public void onFinished(CurrentStats currentStats) {
        if(mainView != null){
            mainView.setDataToShow(currentStats);
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

package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.WorkersContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkersPreseterImpl implements WorkersContract.presenter,
        WorkersContract.GetWorkersIntractor.OnFinishedListener {
    private WorkersContract.MainView mainView;
    private WorkersContract.GetWorkersIntractor getWorkersIntractor;
    private Context context;
    private DataDatabase mDatabase;

    public WorkersPreseterImpl(WorkersContract.MainView mainView,
                               WorkersContract.GetWorkersIntractor getWorkersIntractor,
                               Context context) {
        this.mainView = mainView;
        this.getWorkersIntractor = getWorkersIntractor;
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
        getWorkersIntractor.getWorkers(this, mDatabase, context);
    }

    @Override
    public void requestDataFromServer() {
        mDatabase = new DataDatabase(context) ;
        Date dateLife = new Date();
        Preferences preferences = new Preferences(context);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            dateLife = dateFormat.parse(preferences.getLifeTimeWorkers());
            Log.d("preferences_0", dateLife.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("preferences", preferences.getLifeTimeWorkers());

        if (dateLife.before(new Date())) {
            getWorkersIntractor.getWorkers(this, mDatabase, context);
        } else  {
            mDatabase.getWorkersFromDataBase(this);
        }
    }

    @Override
    public void onFinished(Workers workers) {
        if(mainView != null){
            mainView.setDataToShow(workers);
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
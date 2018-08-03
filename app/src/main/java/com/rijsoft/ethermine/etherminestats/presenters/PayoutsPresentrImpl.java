package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.Utils;
import com.rijsoft.ethermine.etherminestats.contracts.PayoutsContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayoutsPresentrImpl implements PayoutsContract.presenter
        ,PayoutsContract.GetPayoutsIntractor.OnFinishedListener {

    private PayoutsContract.MainView mainView;
    private PayoutsContract.GetPayoutsIntractor getPayoutsIntractor;
    private Context context;
    private DataDatabase mDatabase;

    public PayoutsPresentrImpl(PayoutsContract.MainView mainView,
                               PayoutsContract.GetPayoutsIntractor getPayoutsIntractor,
                               Context context) {
        this.mainView = mainView;
        this.getPayoutsIntractor = getPayoutsIntractor;
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
            getPayoutsIntractor.getPayouts(this, mDatabase, context);
        } else {
            this.onFailure(new Throwable("No connection to internet"));
            mDatabase.getPayoutsFromDataBase(this);
        }

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
            getPayoutsIntractor.getPayouts(this, mDatabase, context);
        } else  {
            mDatabase.getPayoutsFromDataBase(this);
        }
    }

    @Override
    public void onFinished(Payouts payouts) {
        if(mainView != null){
            mainView.setDataToShow(payouts);
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
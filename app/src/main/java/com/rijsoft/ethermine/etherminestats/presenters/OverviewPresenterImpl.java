package com.rijsoft.ethermine.etherminestats.presenters;

import android.content.Context;

import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

public class OverviewPresenterImpl implements
        OverviewContract.presenter,
        OverviewContract.GetDashboardIntractor.OnFinishedListener{

    private OverviewContract.MainView mainView;
    private OverviewContract.GetDashboardIntractor getDashboardIntractor;
    private Context context;

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
        getDashboardIntractor.getOverview(this,context);
    }

    @Override
    public void requestDataFromServer() {
        getDashboardIntractor.getOverview(this,context);
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

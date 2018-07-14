package com.rijsoft.ethermine.etherminestats.contracts;

import android.content.Context;

import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.dashboard.Dashboard;

import java.util.ArrayList;

public interface OverviewContract {

    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToShow(CurrentStats currentStats);

        void onResponseFailure(Throwable throwable);

    }

    interface GetDashboardIntractor {

        interface OnFinishedListener {
            void onFinished(CurrentStats currentStats);
            void onFailure(Throwable t);
        }

        void getOverview(OnFinishedListener onFinishedListener, Context context);
    }

}

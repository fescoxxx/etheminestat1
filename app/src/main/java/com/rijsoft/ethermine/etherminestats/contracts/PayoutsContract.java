package com.rijsoft.ethermine.etherminestats.contracts;

import android.content.Context;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;

public interface PayoutsContract {

    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToShow(Payouts payouts);

        void onResponseFailure(Throwable throwable);

    }

    interface GetPayoutsIntractor {

        interface OnFinishedListener {
            void onFinished(Payouts payouts);
            void onFailure(Throwable t);
        }

        void getPayouts(OnFinishedListener onFinishedListener,
                         DataDatabase database,
                         Context context);
    }

}

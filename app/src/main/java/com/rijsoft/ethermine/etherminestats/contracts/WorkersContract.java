package com.rijsoft.ethermine.etherminestats.contracts;

import android.content.Context;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

public interface WorkersContract {


    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToShow(Workers workers);

        void onResponseFailure(Throwable throwable);

    }

    interface GetWorkersIntractor {

        interface OnFinishedListener {
            void onFinished(Workers workers);
            void onFailure(Throwable t);
        }

        void getWorkers(OnFinishedListener onFinishedListener,
                        DataDatabase database,
                        Context context);
    }
}

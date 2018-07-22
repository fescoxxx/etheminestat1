package com.rijsoft.ethermine.etherminestats.contracts;

import android.content.Context;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;


public interface SettingsContract {


    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToShow(Settings settings);

        void onResponseFailure(Throwable throwable);

    }

    interface GetSettingsIntractor {

        interface OnFinishedListener {
            void onFinished(Settings settings);
            void onFailure(Throwable t);
        }

        void getSettings(SettingsContract.GetSettingsIntractor.OnFinishedListener onFinishedListener,
                        DataDatabase database,
                        Context context);
    }
}

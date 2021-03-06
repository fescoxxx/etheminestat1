package com.rijsoft.ethermine.etherminestats.contracts;

import android.content.Context;
import android.view.View;

import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;


public interface SettingsContract {


    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void onClickOk(View view, String wallet);

        void requestDataFromServer();

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToShow(Settings settings, String tagAction);

        void onResponseFailure(Throwable throwable);

    }

    interface GetSettingsIntractor {

        interface OnFinishedListener {
            void onFinished(Settings settings, String tagAction);
            void onFailure(Throwable t);
        }

        void getSettings(SettingsContract.GetSettingsIntractor.OnFinishedListener onFinishedListener,
                        DataDatabase database,
                        Context context,
                        String tagAction);
    }
}

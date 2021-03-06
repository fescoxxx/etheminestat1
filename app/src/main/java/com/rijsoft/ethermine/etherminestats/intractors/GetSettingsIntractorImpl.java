package com.rijsoft.ethermine.etherminestats.intractors;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.SettingsContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.database.Settings.SettingsSaveIntoDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rijsoft.ethermine.etherminestats.Preferences.LIFE_TIME_SETTINGS;

public class GetSettingsIntractorImpl implements SettingsContract.GetSettingsIntractor {
    private Context context;
    private Preferences preferences;
    private String tagAction;

    @Override
    public void getSettings(final OnFinishedListener onFinishedListener,
                            final DataDatabase database,
                            Context context,
                            final String tagAction) {
        this.context = context;
        this.preferences = new Preferences(context);
        this.tagAction = tagAction;
        ApiService service = RetrofitClient.getClient().create(ApiService.class);

        Call<Settings> settingsCall = service.getSettings(preferences.getMiner());

        Log.wtf("URL Called", settingsCall.request().url() + "");

        settingsCall.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {

                assert response.body() != null;
                try {
                    if (response.body().getStatus().equals("OK")) {
                        onFinishedListener.onFinished(response.body(), tagAction);
                        preferences.updateDateLife(LIFE_TIME_SETTINGS);
                        database.clearSettings();
                        SettingsSaveIntoDatabase task = new SettingsSaveIntoDatabase(database);
                        task.execute(response.body());
                    } else {
                        onFinishedListener.onFailure(new Throwable("Error loading data. Check your account settings"));
                    }
                } catch (Exception ex) {
                    onFinishedListener.onFinished(new Settings(), tagAction);
                    onFinishedListener.onFailure(new Throwable("Error loading data. Check your account settings"));
                }
            }
            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                if(t.getMessage().contains("address associated with hostname")){
                    onFinishedListener.onFinished(new Settings(), tagAction);
                    onFinishedListener.onFailure(new Throwable("No connection to internet"));
                } else {
                    onFinishedListener.onFinished(new Settings(), tagAction);
                    onFinishedListener.onFailure(new Throwable("No data"));
                }
            }
        });
    }
}

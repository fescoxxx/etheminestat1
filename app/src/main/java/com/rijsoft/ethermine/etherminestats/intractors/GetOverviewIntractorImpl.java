package com.rijsoft.ethermine.etherminestats.intractors;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.database.currenStats.CurrentStatsSaveIntoDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rijsoft.ethermine.etherminestats.Preferences.LIFE_TIME_OVERVIEW;

public class GetOverviewIntractorImpl implements OverviewContract.GetDashboardIntractor {

    private Context context;
    private Preferences preferences;
    @Override
    public void getOverview(final OnFinishedListener onFinishedListener,
                            final DataDatabase database,
                            final Context context) {
        this.context = context;
        this.preferences = new Preferences(context);
        ApiService service = RetrofitClient.getClient().create(ApiService.class);

        Call<CurrentStats> callCurrentStatistics = service.getCurrentStats(preferences.getMiner());

        Log.wtf("URL Called", callCurrentStatistics.request().url() + "");

        callCurrentStatistics.enqueue(new Callback<CurrentStats>() {
            @Override
            public void onResponse(Call<CurrentStats> call, Response<CurrentStats> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("OK")) {
                    onFinishedListener.onFinished(response.body());
                    preferences.updateDateLife(LIFE_TIME_OVERVIEW);
                   // database.clearCurrentStats();
                    CurrentStatsSaveIntoDatabase task = new CurrentStatsSaveIntoDatabase(database);
                    task.execute(response.body());
                } else {
                    onFinishedListener.onFailure(new Throwable("Error loading data. Check your account settings"));
                }
            }

            @Override
            public void onFailure(Call<CurrentStats> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}


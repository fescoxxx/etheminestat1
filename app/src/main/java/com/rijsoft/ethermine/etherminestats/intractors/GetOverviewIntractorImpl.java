package com.rijsoft.ethermine.etherminestats.intractors;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.contracts.OverviewContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetOverviewIntractorImpl implements OverviewContract.GetDashboardIntractor {
    private DataDatabase mDatabase;

    @Override
    public void getOverview(final OnFinishedListener onFinishedListener, Context context) {

        mDatabase  = new DataDatabase(context);

        mDatabase.insertIntoCurrentStats();
        mDatabase.getCurrentStatsFromDataBase(onFinishedListener);


        ApiService service = RetrofitClient.getClient().create(ApiService.class);

        Call<CurrentStats> callCurrentStatistics = service.getCurrentStats("1a5a8d5dd2e5cddc177299eb01c961d77fd5cd8f");

        Log.wtf("URL Called", callCurrentStatistics.request().url() + "");

        callCurrentStatistics.enqueue(new Callback<CurrentStats>() {
            @Override
            public void onResponse(Call<CurrentStats> call, Response<CurrentStats> response) {
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<CurrentStats> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }
}


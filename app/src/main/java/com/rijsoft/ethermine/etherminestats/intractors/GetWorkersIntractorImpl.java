package com.rijsoft.ethermine.etherminestats.intractors;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.WorkersContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.database.workers.WorkersSaveIntoDatebase;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rijsoft.ethermine.etherminestats.Preferences.LIFE_TIME_WORKERS;

public class GetWorkersIntractorImpl implements WorkersContract.GetWorkersIntractor {
    private Context context;
    private Preferences preferences;

    @Override
    public void getWorkers(final OnFinishedListener onFinishedListener,
                           final DataDatabase database,
                           Context context) {
        this.context = context;
        this.preferences = new Preferences(context);
        ApiService service = RetrofitClient.getClient().create(ApiService.class);
        Call<Workers> workersCall = service.getWorkers(preferences.getMiner());

        Log.wtf("URL Called", workersCall.request().url() + "");

        workersCall.enqueue(new Callback<Workers>() {
            @Override
            public void onResponse(Call<Workers> call, Response<Workers> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("OK")) {
                    onFinishedListener.onFinished(response.body());
                    preferences.updateDateLife(LIFE_TIME_WORKERS);
                    database.clearWorkers();
                    WorkersSaveIntoDatebase task = new WorkersSaveIntoDatebase(database);
                    task.execute(response.body());
                }else {
                    onFinishedListener.onFailure(new Throwable("Error loading data. Check your account settings"));
                }
            }
            @Override
            public void onFailure(Call<Workers> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });



    }
}

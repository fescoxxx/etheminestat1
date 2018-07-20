package com.rijsoft.ethermine.etherminestats.intractors;

import android.content.Context;
import android.util.Log;

import com.rijsoft.ethermine.etherminestats.Preferences;
import com.rijsoft.ethermine.etherminestats.contracts.PayoutsContract;
import com.rijsoft.ethermine.etherminestats.database.DataDatabase;
import com.rijsoft.ethermine.etherminestats.database.payouts.PayoutsSaveIntoDatabase;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;
import com.rijsoft.ethermine.etherminestats.remote.RetrofitClient;
import com.rijsoft.ethermine.etherminestats.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rijsoft.ethermine.etherminestats.Preferences.LIFE_TIME_PAYOUTS;

public class GetPayoutsIntractorImpl implements PayoutsContract.GetPayoutsIntractor{
    private Context context;
    private Preferences preferences;

    @Override
    public void getPayouts(final OnFinishedListener onFinishedListener,
                           final DataDatabase database,
                           final Context context) {
        this.context = context;
        this.preferences = new Preferences(context);
        ApiService service = RetrofitClient.getClient().create(ApiService.class);
        Call<Payouts> callPayouts = service.getPayouts(preferences.getMiner()) ;

        Log.wtf("URL Called", callPayouts.request().url() + "");

        callPayouts.enqueue(new Callback<Payouts>() {
            @Override
            public void onResponse(Call<Payouts> call, Response<Payouts> response) {
                if (response.body().getStatus().equals("OK")) {
                    onFinishedListener.onFinished(response.body());
                    preferences.updateDateLife(LIFE_TIME_PAYOUTS);
                    database.clearPayouts();
                    PayoutsSaveIntoDatabase task = new PayoutsSaveIntoDatabase(database);
                    task.execute(response.body());
                    } else {
                        onFinishedListener.onFailure(new Throwable("Error loading data. Check your account settings"));
                    }
                }
            @Override
            public void onFailure(Call<Payouts> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}

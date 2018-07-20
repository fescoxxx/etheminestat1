package com.rijsoft.ethermine.etherminestats.service;

import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;
import com.rijsoft.ethermine.etherminestats.model.dashboard.Dashboard;
import com.rijsoft.ethermine.etherminestats.model.history.History;
import com.rijsoft.ethermine.etherminestats.model.payouts.Payouts;
import com.rijsoft.ethermine.etherminestats.model.settings.Settings;
import com.rijsoft.ethermine.etherminestats.model.workers.Workers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/miner/{miner}/dashboard")
    Call<Dashboard> getDashboard(@Path("miner") String miner);

    @GET("/miner/{miner}/currentStats")
    Call<CurrentStats> getCurrentStats(@Path("miner") String miner);

    @GET("/miner/{miner}/settings")
    Call<Settings> getSettings(@Path("miner") String miner);

    @GET("/miner/{miner}/history")
    Call<History> getHistory(@Path("miner") String miner);

    @GET("/miner/{miner}/payouts")
    Call<Payouts> getPayouts(@Path("miner") String miner);

    @GET("/miner/{miner}/workers")
    Call<Workers> getWorkers(@Path("miner") String miner);
}

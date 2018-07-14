package com.rijsoft.ethermine.etherminestats.model.dashboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("settings")
    private Settings settings;
    @SerializedName("currentStatistics")
    private CurrentStatistics currentStatistics;
    @SerializedName("workers")
    private List<Workers> workers;
    @SerializedName("statistics")
    private List<Statistics> statistics;

    public Settings getSettings ()
    {
        return settings;
    }

    public void setSettings (Settings settings)
    {
        this.settings = settings;
    }

    public CurrentStatistics getCurrentStatistics ()
    {
        return currentStatistics;
    }

    public void setCurrentStatistics (CurrentStatistics currentStatistics)
    {
        this.currentStatistics = currentStatistics;
    }

    public List<Workers> getWorkers ()
    {
        return workers;
    }

    public void setWorkers (List<Workers> workers)
    {
        this.workers = workers;
    }

    public List<Statistics> getStatistics ()
    {
        return statistics;
    }

    public void setStatistics (List<Statistics> statistics)
    {
        this.statistics = statistics;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [settings = "+settings+", currentStatistics = "+currentStatistics+", workers = "+workers+", statistics = "+statistics+"]";
    }
}
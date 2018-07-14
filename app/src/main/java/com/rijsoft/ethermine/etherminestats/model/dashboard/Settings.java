package com.rijsoft.ethermine.etherminestats.model.dashboard;

import com.google.gson.annotations.SerializedName;

public class Settings {
    @SerializedName("minPayout")
    private String minPayout;
    @SerializedName("monitor")
    private String monitor;
    @SerializedName("email")
    private String email;

    public String getMinPayout ()
    {
        return minPayout;
    }

    public void setMinPayout (String minPayout)
    {
        this.minPayout = minPayout;
    }

    public String getMonitor ()
    {
        return monitor;
    }

    public void setMonitor (String monitor)
    {
        this.monitor = monitor;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minPayout = "+minPayout+", monitor = "+monitor+", email = "+email+"]";
    }
}
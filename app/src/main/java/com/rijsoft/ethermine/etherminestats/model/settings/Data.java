package com.rijsoft.ethermine.etherminestats.model.settings;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("minPayout")
    private String minPayout;
    @SerializedName("monitor")
    private String monitor;
    @SerializedName("email")
    private String email;
    @SerializedName("ip")
    private String ip;

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

    public String getIp ()
    {
        return ip;
    }

    public void setIp (String ip)
    {
        this.ip = ip;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minPayout = "+minPayout+", monitor = "+monitor+", email = "+email+", ip = "+ip+"]";
    }
}
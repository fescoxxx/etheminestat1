package com.rijsoft.ethermine.etherminestats.model.dashboard;

import com.google.gson.annotations.SerializedName;

public class Dashboard {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private Data data;

    public Dashboard(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", data = "+data+"]";
    }
}
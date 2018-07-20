package com.rijsoft.ethermine.etherminestats.model.currentStats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentStats implements Serializable{
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private Data data;

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
        return "{\"status\" : "+ "\""+status+"\""+", \"data\": "+data+"}";
    }
}
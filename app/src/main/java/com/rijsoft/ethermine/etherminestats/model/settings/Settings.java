package com.rijsoft.ethermine.etherminestats.model.settings;

import com.google.gson.annotations.SerializedName;

public class Settings {
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
        return "{\"status\" : "+ "\""+status+ "\""+", \"data\" : "+data+"}";
    }
}
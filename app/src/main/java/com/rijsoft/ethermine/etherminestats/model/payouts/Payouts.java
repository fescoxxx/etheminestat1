package com.rijsoft.ethermine.etherminestats.model.payouts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Payouts {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<Data> data;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public  List<Data> getData ()
    {
        return data;
    }

    public void setData ( List<Data> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "{\"status\" : "+ "\""+status+"\""+", \"data\" : "+data+"}";
    }
}
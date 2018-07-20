package com.rijsoft.ethermine.etherminestats.model.workers;

import java.io.Serializable;
import java.util.List;

public class Workers implements Serializable {
    private String status;

    private List<Data> data;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "{\"status\" : "+ "\""+status+"\""+", \"data\" : "+data+"}";
    }
}

package com.rijsoft.ethermine.etherminestats.model.workers;

import java.io.Serializable;

public class Data implements Serializable{
    private String lastSeen;

    private String time;

    private String averageHashrate;

    private String invalidShares;

    private String validShares;

    private String currentHashrate;

    private String worker;

    private String staleShares;

    private String reportedHashrate;

    public String getLastSeen ()
    {
        return lastSeen;
    }

    public void setLastSeen (String lastSeen)
    {
        this.lastSeen = lastSeen;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getAverageHashrate ()
    {
        return averageHashrate;
    }

    public void setAverageHashrate (String averageHashrate)
    {
        this.averageHashrate = averageHashrate;
    }

    public String getInvalidShares ()
    {
        return invalidShares;
    }

    public void setInvalidShares (String invalidShares)
    {
        this.invalidShares = invalidShares;
    }

    public String getValidShares ()
    {
        return validShares;
    }

    public void setValidShares (String validShares)
    {
        this.validShares = validShares;
    }

    public String getCurrentHashrate ()
    {
        return currentHashrate;
    }

    public void setCurrentHashrate (String currentHashrate)
    {
        this.currentHashrate = currentHashrate;
    }

    public String getWorker ()
    {
        return worker;
    }

    public void setWorker (String worker)
    {
        this.worker = worker;
    }

    public String getStaleShares ()
    {
        return staleShares;
    }

    public void setStaleShares (String staleShares)
    {
        this.staleShares = staleShares;
    }

    public String getReportedHashrate ()
    {
        return reportedHashrate;
    }

    public void setReportedHashrate (String reportedHashrate)
    {
        this.reportedHashrate = reportedHashrate;
    }

    @Override
    public String toString()
    {
        return "{\"lastSeen\" : "+lastSeen+", \"time\" : "+time+", \"averageHashrate\" : "+averageHashrate+", \"invalidShares\" : "+invalidShares+", \"validShares\" : "+validShares+", \"currentHashrate\" : "+currentHashrate+", \"worker\" : "+ "\""+worker+"\""+", \"staleShares\" : "+staleShares+", \"reportedHashrate\" : "+reportedHashrate+"}";
    }
}
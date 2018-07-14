package com.rijsoft.ethermine.etherminestats.model.history;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("time")
    private String time;
    @SerializedName("activeWorkers")
    private String activeWorkers;
    @SerializedName("averageHashrate")
    private String averageHashrate;
    @SerializedName("invalidShares")
    private String invalidShares;
    @SerializedName("validShares")
    private String validShares;
    @SerializedName("currentHashrate")
    private String currentHashrate;
    @SerializedName("staleShares")
    private String staleShares;
    @SerializedName("reportedHashrate")
    private String reportedHashrate;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getActiveWorkers ()
    {
        return activeWorkers;
    }

    public void setActiveWorkers (String activeWorkers)
    {
        this.activeWorkers = activeWorkers;
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
        return "ClassPojo [time = "+time+", activeWorkers = "+activeWorkers+", averageHashrate = "+averageHashrate+", invalidShares = "+invalidShares+", validShares = "+validShares+", currentHashrate = "+currentHashrate+", staleShares = "+staleShares+", reportedHashrate = "+reportedHashrate+"]";
    }
}
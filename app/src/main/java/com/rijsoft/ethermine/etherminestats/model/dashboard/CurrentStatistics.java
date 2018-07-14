package com.rijsoft.ethermine.etherminestats.model.dashboard;

import com.google.gson.annotations.SerializedName;

public class CurrentStatistics {
    @SerializedName("lastSeen")
    private String lastSeen;
    @SerializedName("time")
    private String time;
    @SerializedName("activeWorkers")
    private String activeWorkers;
    @SerializedName("invalidShares")
    private String invalidShares;
    @SerializedName("validShares")
    private String validShares;
    @SerializedName("currentHashrate")
    private String currentHashrate;
    @SerializedName("unpaid")
    private String unpaid;
    @SerializedName("staleShares")
    private String staleShares;
    @SerializedName("reportedHashrate")
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

    public String getActiveWorkers ()
    {
        return activeWorkers;
    }

    public void setActiveWorkers (String activeWorkers)
    {
        this.activeWorkers = activeWorkers;
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

    public String getUnpaid ()
    {
        return unpaid;
    }

    public void setUnpaid (String unpaid)
    {
        this.unpaid = unpaid;
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
        return "ClassPojo [lastSeen = "+lastSeen+", time = "+time+", activeWorkers = "+activeWorkers+", invalidShares = "+invalidShares+", validShares = "+validShares+", currentHashrate = "+currentHashrate+", unpaid = "+unpaid+", staleShares = "+staleShares+", reportedHashrate = "+reportedHashrate+"]";
    }
}
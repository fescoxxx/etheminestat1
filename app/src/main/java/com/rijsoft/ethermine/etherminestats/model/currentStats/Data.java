package com.rijsoft.ethermine.etherminestats.model.currentStats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {
    @SerializedName("lastSeen")
    private String lastSeen;
    @SerializedName("usdPerMin")
    private String usdPerMin;
    @SerializedName("btcPerMin")
    private String btcPerMin;
    @SerializedName("averageHashrate")
    private String averageHashrate;
    @SerializedName("activeWorkers")
    private String activeWorkers;
    @SerializedName("validShares")
    private String validShares;
    @SerializedName("reportedHashrate")
    private String reportedHashrate;
    @SerializedName("unconfirmed")
    private String unconfirmed;
    @SerializedName("time")
    private String time;
    @SerializedName("invalidShares")
    private String invalidShares;
    @SerializedName("currentHashrate")
    private String currentHashrate;
    @SerializedName("coinsPerMin")
    private String coinsPerMin;
    @SerializedName("unpaid")
    private String unpaid;
    @SerializedName("staleShares")
    private String staleShares;

    public String getLastSeen ()
    {
        return lastSeen;
    }

    public void setLastSeen (String lastSeen)
    {
        this.lastSeen = lastSeen;
    }

    public String getUsdPerMin ()
    {
        return usdPerMin;
    }

    public void setUsdPerMin (String usdPerMin)
    {
        this.usdPerMin = usdPerMin;
    }

    public String getBtcPerMin ()
    {
        return btcPerMin;
    }

    public void setBtcPerMin (String btcPerMin)
    {
        this.btcPerMin = btcPerMin;
    }

    public String getAverageHashrate ()
    {
        return averageHashrate;
    }

    public void setAverageHashrate (String averageHashrate)
    {
        this.averageHashrate = averageHashrate;
    }

    public String getActiveWorkers ()
    {
        return activeWorkers;
    }

    public void setActiveWorkers (String activeWorkers)
    {
        this.activeWorkers = activeWorkers;
    }

    public String getValidShares ()
    {
        return validShares;
    }

    public void setValidShares (String validShares)
    {
        this.validShares = validShares;
    }

    public String getReportedHashrate ()
    {
        return reportedHashrate;
    }

    public void setReportedHashrate (String reportedHashrate)
    {
        this.reportedHashrate = reportedHashrate;
    }

    public String getUnconfirmed ()
    {
        return unconfirmed;
    }

    public void setUnconfirmed (String unconfirmed)
    {
        this.unconfirmed = unconfirmed;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getInvalidShares ()
    {
        return invalidShares;
    }

    public void setInvalidShares (String invalidShares)
    {
        this.invalidShares = invalidShares;
    }

    public String getCurrentHashrate ()
    {
        return currentHashrate;
    }

    public void setCurrentHashrate (String currentHashrate)
    {
        this.currentHashrate = currentHashrate;
    }

    public String getCoinsPerMin ()
    {
        return coinsPerMin;
    }

    public void setCoinsPerMin (String coinsPerMin)
    {
        this.coinsPerMin = coinsPerMin;
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

    @Override
    public String toString()
    {
        return "ClassPojo [lastSeen = "+lastSeen+", usdPerMin = "+usdPerMin+", btcPerMin = "+btcPerMin+", averageHashrate = "+averageHashrate+", activeWorkers = "+activeWorkers+", validShares = "+validShares+", reportedHashrate = "+reportedHashrate+", unconfirmed = "+unconfirmed+", time = "+time+", invalidShares = "+invalidShares+", currentHashrate = "+currentHashrate+", coinsPerMin = "+coinsPerMin+", unpaid = "+unpaid+", staleShares = "+staleShares+"]";
    }
}

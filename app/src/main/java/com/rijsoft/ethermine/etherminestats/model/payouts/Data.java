package com.rijsoft.ethermine.etherminestats.model.payouts;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("amount")
    private String amount;
    @SerializedName("start")
    private String start;
    @SerializedName("txHash")
    private String txHash;
    @SerializedName("paidOn")
    private String paidOn;
    @SerializedName("end")
    private String end;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public String getTxHash ()
    {
        return txHash;
    }

    public void setTxHash (String txHash)
    {
        this.txHash = txHash;
    }

    public String getPaidOn ()
    {
        return paidOn;
    }

    public void setPaidOn (String paidOn)
    {
        this.paidOn = paidOn;
    }

    public String getEnd ()
    {
        return end;
    }

    public void setEnd (String end)
    {
        this.end = end;
    }

    @Override
    public String toString()
    {
        return "{\"amount\" : "+amount+", \"start\" : "+start+", \"txHash\" : "+ "\""+txHash+"\""+", \"paidOn\" : "+paidOn+", \"end\" : "+end+"}";
    }
}
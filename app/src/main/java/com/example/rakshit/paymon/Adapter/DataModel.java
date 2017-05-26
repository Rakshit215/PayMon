package com.example.rakshit.paymon.Adapter;

/**
 * Created by Rakshit on 13-04-2017.
 */
public class DataModel
{
    String talktime;
    String validity;
    String amt;

    public DataModel(String tt, String v, String amt )
    {
        this.talktime = tt;
        this.validity = v;
        this.amt =amt;

    }


    public String getTalktime() {
        return talktime;
    }

    public String getValidity() {
        return validity;
    }

    public String getAmt() {
        return amt;
    }
}

package com.skyzone18.ManMandirVidyalay.Studentlogin.Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kevalt on 2/20/2018.
 */

public class LoginExample {
    @SerializedName("Sucess")
    @Expose
    private String sucess;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Gr_no1")
    @Expose
    private String grNo1;
    @SerializedName("ExSeatNo")
    @Expose
    private String exSeatNo;
    @SerializedName("Std")
    @Expose
    private String std;
    @SerializedName("Div")
    @Expose
    private String div;
    @SerializedName("Result")
    @Expose
    private String result;
    @SerializedName("TotalMax")
    @Expose
    private String totalMax;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Percent")
    @Expose
    private String percent;
    @SerializedName("Rank")
    @Expose
    private String rank;
    @SerializedName("totalcollection")
    @Expose
    private String totalcollection;
    @SerializedName("totalmafi")
    @Expose
    private String totalmafi;



    @SerializedName("data")
    @Expose
    private List<LoginDatum> data = null;

    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public List<LoginDatum> getData() {
        return data;
    }

    public void setData(List<LoginDatum> data) {
        this.data = data;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGrNo1() {
        return grNo1;
    }

    public void setGrNo1(String grNo1) {
        this.grNo1 = grNo1;
    }

    public String getExSeatNo() {
        return exSeatNo;
    }

    public void setExSeatNo(String exSeatNo) {
        this.exSeatNo = exSeatNo;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTotalMax() {
        return totalMax;
    }

    public void setTotalMax(String totalMax) {
        this.totalMax = totalMax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getTotalcollection() {
        return totalcollection;
    }

    public void setTotalcollection(String totalcollection) {
        this.totalcollection = totalcollection;
    }

    public String getTotalmafi() {
        return totalmafi;
    }

    public void setTotalmafi(String totalmafi) {
        this.totalmafi = totalmafi;
    }

}


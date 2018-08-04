package com.skyzone18.ManMandirVidyalay.Studentlogin.Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kevalt on 2/20/2018.
 */

public class LoginDatum {
    @SerializedName("GrNO")
    @Expose
    private String grNO;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("EngName")
    @Expose
    private String engName;
    @SerializedName("Birthdate")
    @Expose
    private String birthdate;
    @SerializedName("Engdate")
    @Expose
    private String engdate;
    @SerializedName("Sex")
    @Expose
    private String sex;
    @SerializedName("Cast")
    @Expose
    private String cast;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("cur_std")
    @Expose
    private String curStd;
    @SerializedName("cur_div")
    @Expose
    private String curDiv;
    @SerializedName("RollNo")
    @Expose
    private String rollNo;
    @SerializedName("StdAdharID")
    @Expose
    private String stdAdharID;
    @SerializedName("Religion")
    @Expose
    private String religion;
    @SerializedName("Taluko")
    @Expose
    private String taluko;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("constr")
    @Expose
    private String constr;
    @SerializedName("mainstring")
    @Expose
    private String mainstring;
    @SerializedName("img")
    @Expose
    private String img;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getGrNO() {
        return grNO;
    }

    public void setGrNO(String grNO) {
        this.grNO = grNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEngdate() {
        return engdate;
    }

    public void setEngdate(String engdate) {
        this.engdate = engdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurStd() {
        return curStd;
    }

    public void setCurStd(String curStd) {
        this.curStd = curStd;
    }

    public String getCurDiv() {
        return curDiv;
    }

    public void setCurDiv(String curDiv) {
        this.curDiv = curDiv;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getStdAdharID() {
        return stdAdharID;
    }

    public void setStdAdharID(String stdAdharID) {
        this.stdAdharID = stdAdharID;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTaluko() {
        return taluko;
    }

    public void setTaluko(String taluko) {
        this.taluko = taluko;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConstr() {
        return constr;
    }

    public void setConstr(String constr) {
        this.constr = constr;
    }
    public String getMainstring() {
        return mainstring;
    }

    public void setMainstring(String mainstring) {
        this.mainstring = mainstring;
    }
 // exam list


    @SerializedName("ExamName")
    @Expose
    private String examName;
    @SerializedName("ExamCode")
    @Expose
    private String examCode;

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }



    /// exam result

    @SerializedName("SubName")
    @Expose
    private String subName;
    @SerializedName("TotalTh")
    @Expose
    private String totalTh;
    @SerializedName("MinTh")
    @Expose
    private String minTh;
    @SerializedName("ObtainedTh")
    @Expose
    private String obtainedTh;
    @SerializedName("SubNameEng")
    @Expose
    private Object subNameEng;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getTotalTh() {
        return totalTh;
    }

    public void setTotalTh(String totalTh) {
        this.totalTh = totalTh;
    }

    public String getMinTh() {
        return minTh;
    }

    public void setMinTh(String minTh) {
        this.minTh = minTh;
    }

    public String getObtainedTh() {
        return obtainedTh;
    }

    public void setObtainedTh(String obtainedTh) {
        this.obtainedTh = obtainedTh;
    }

    public Object getSubNameEng() {
        return subNameEng;
    }

    public void setSubNameEng(Object subNameEng) {
        this.subNameEng = subNameEng;
    }

    // respt no

    @SerializedName("ReceiptNo")
    @Expose
    private String receiptNo;
    @SerializedName("RecDate")
    @Expose
    private String recDate;
    @SerializedName("AdmissionFees")
    @Expose
    private String admissionFees;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Mafi")
    @Expose
    private String mafi;

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public String getAdmissionFees() {
        return admissionFees;
    }

    public void setAdmissionFees(String admissionFees) {
        this.admissionFees = admissionFees;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMafi() {
        return mafi;
    }

    public void setMafi(String mafi) {
        this.mafi = mafi;
    }



    // fees full info
    @SerializedName("AdmissionFees1")
    @Expose
    private String admissionFees1;
    @SerializedName("TermFees")
    @Expose
    private String termFees;
    @SerializedName("EnrFee")
    @Expose
    private String enrFee;
    @SerializedName("ExamFees")
    @Expose
    private String examFees;
    @SerializedName("LabFee")
    @Expose
    private String labFee;
    @SerializedName("CompFees")
    @Expose
    private String compFees;
    @SerializedName("EduFees")
    @Expose
    private String eduFees;
    @SerializedName("Activityfee")
    @Expose
    private String activityfee;
    @SerializedName("otherfee")
    @Expose
    private String otherfee;
    @SerializedName("Mafi1")
    @Expose
    private String mafi1;

    public String getAdmissionFees1() {
        return admissionFees1;
    }

    public void setAdmissionFees1(String admissionFees1) {
        this.admissionFees1 = admissionFees1;
    }

    public String getTermFees() {
        return termFees;
    }

    public void setTermFees(String termFees) {
        this.termFees = termFees;
    }

    public String getEnrFee() {
        return enrFee;
    }

    public void setEnrFee(String enrFee) {
        this.enrFee = enrFee;
    }

    public String getExamFees() {
        return examFees;
    }

    public void setExamFees(String examFees) {
        this.examFees = examFees;
    }

    public String getLabFee() {
        return labFee;
    }

    public void setLabFee(String labFee) {
        this.labFee = labFee;
    }

    public String getCompFees() {
        return compFees;
    }

    public void setCompFees(String compFees) {
        this.compFees = compFees;
    }

    public String getEduFees() {
        return eduFees;
    }

    public void setEduFees(String eduFees) {
        this.eduFees = eduFees;
    }

    public String getActivityfee() {
        return activityfee;
    }

    public void setActivityfee(String activityfee) {
        this.activityfee = activityfee;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getMafi1() {
        return mafi1;
    }

    public void setMafi1(String mafi1) {
        this.mafi1 = mafi1;
    }
}

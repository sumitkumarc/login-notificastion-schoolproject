package com.skyzone18.ManMandirVidyalay.Studentlogin.Rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sumit on 2/2/2018.
 */

public interface LoginApiService {
    @GET("AppServices/AppWebService.asmx/Catagory")
    Call<LoginExample> getAllCategory();

    @FormUrlEncoded
    @POST("WebService.asmx/GetPhoneNo1")
    Call<LoginExample> getPhonelogin(@Field("username") String userno, @Field("schoolname") String schoolname);

    @FormUrlEncoded
    @POST("WebService.asmx/GetExameList")
    Call<LoginExample> GetExamList(@Field("gr_no") String grno, @Field("source") String constrin);

    @FormUrlEncoded
    @POST("WebService.asmx/GetFasaExameList")
    Call<LoginExample> GetFasaExameList(@Field("source") String CONSTRIN, @Field("gr_no") String GRNO);

    @FormUrlEncoded
    @POST("WebService.asmx/GetResult")
    Call<LoginExample> GetExameResult(@Field("source") String user_datascrept,
                                      @Field("Gr_No") String grno,
                                      @Field("ExamCode") String examcode,
                                      @Field("exametype01") Integer examtype);
    @FormUrlEncoded
    @POST("WebService.asmx/GetFees")
    Call<LoginExample> GetFeesData(@Field("source") String constrin,
                                   @Field("Gr_No") String grno);
    @FormUrlEncoded
    @POST("WebService.asmx/GetFeeDetail")
    Call<LoginExample> GetFeesMoreInfo(@Field("recieptno") String posdata, @Field("source") String constrin, @Field("Gr_No") String grno);

    @FormUrlEncoded
    @POST("WebService.asmx/PostProfile")
    Call<LoginExample> getuserinfo(@Field("Gr_No") String grno, @Field("source") String constrin);
}

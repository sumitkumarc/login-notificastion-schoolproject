package com.skyzone18.ManMandirVidyalay.Rest;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sumit on 2/2/2018.
 */

public interface ApiService {
    @GET("web_service/get_gallery_title")
    Call<Example> getAllCategory();

    @FormUrlEncoded
    @POST("web_service/get_titlewise_photo")
    Call<Example> getAllPhoto(@Field("cat_id") String image_id);


    @GET("web_service//get_notice")
    Call<Example> GetNotice();



    @GET("web_service/get_medium")
    Call<Example> GetSection();

    @GET("web_service/get_news")
    Call<Example> GetNews();

    @GET("web_service/get_assignment")
    Call<Example> GetAssignment();

    @GET("web_service/get_event")
    Call<Example> GetEvent();

    @GET("web_service/get_slider")
    Call<Example> GetSlider();

    @GET("web_service/get_staff")
    Call<Example> GetStaff();


//    @GET("AppServices/AppWebService.asmx/Calander")
//    Call<Example> GetCalander();

    @GET("web_service/get_videos")
    Call<Example> GetVideo();

    @GET("web_service/get_today_event")
    Call<Example> GetTodayEvent();


    @GET("web_service/get_news")
    Call<Example> Getodaynews();

    @GET("web_service/get_toppers")
    Call<Example> GetSchoolRanker();


    @GET("web_service/get_medium")
    Call<Example> GetMedium();

    @FormUrlEncoded
    @POST("web_service/get_medium_wise_standard")
    Call<Example> GetStandard(@Field("medium_id") String stdid);

    @FormUrlEncoded
    @POST("web_service/get_std_wise_homework")
    Call<Example> GetHWork(@Field("standard_id") String Stdname);

    @FormUrlEncoded
    @POST("web_service/save_notification_key")
    Call<Example> GetNotification(@Field("reg_id") String stdid);
}

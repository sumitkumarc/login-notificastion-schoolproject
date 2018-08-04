package com.skyzone18.ManMandirVidyalay.Rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kevalt on 2/20/2018.
 */

public class Datum {

    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("video_code")
    @Expose
    private String videoCode;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }
    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("education")
    @Expose
    private String education;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


    @SerializedName("slider_id")
    @Expose
    private String sliderId;
    @SerializedName("slider_image")
    @Expose
    private String sliderImage;
    @SerializedName("image_path")
    @Expose
    private String imagePath;

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderImage() {
        return sliderImage;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @SerializedName("gallery_title")
    @Expose
    private String galleryTitle;
    @SerializedName("cat_id")
    @Expose
    private String catId;

    public String getGalleryTitle() {
        return galleryTitle;
    }

    public void setGalleryTitle(String galleryTitle) {
        this.galleryTitle = galleryTitle;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }


    @SerializedName("image_id")
    @Expose
    private String imageId;
    @SerializedName("image_name")
    @Expose
    private String imageName;


    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @SerializedName("news_id")
    @Expose
    private String newsId;
    @SerializedName("news_title")
    @Expose
    private String newsTitle;
    @SerializedName("news_image")
    @Expose
    private String newsImage;
    @SerializedName("news_description")
    @Expose
    private String newsDescription;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @SerializedName("notice_id")
    @Expose
    private String noticeId;
    @SerializedName("notice_name")
    @Expose
    private String noticeName;
    @SerializedName("notice_image")
    @Expose
    private String noticeImage;
    @SerializedName("notice_description")
    @Expose
    private String noticeDescription;


    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeImage() {
        return noticeImage;
    }

    public void setNoticeImage(String noticeImage) {
        this.noticeImage = noticeImage;
    }

    public String getNoticeDescription() {
        return noticeDescription;
    }

    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }

    @SerializedName("medium_id")
    @Expose
    private String mediumId;
    @SerializedName("medium_name")
    @Expose
    private String mediumName;

    public String getMediumId() {
        return mediumId;
    }

    public void setMediumId(String mediumId) {
        this.mediumId = mediumId;
    }

    public String getMediumName() {
        return mediumName;
    }

    public void setMediumName(String mediumName) {
        this.mediumName = mediumName;
    }

    @SerializedName("std_id")
    @Expose
    private String stdId;
    @SerializedName("std_name")
    @Expose
    private String stdName;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }
    @SerializedName("homework_id")
    @Expose
    private String homeworkId;
    @SerializedName("homework_note")
    @Expose
    private String homeworkNote;
    @SerializedName("homework_image")
    @Expose
    private String homeworkImage;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    @SerializedName("homework_date")
    @Expose
    private String homeworkDate;
    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkNote() {
        return homeworkNote;
    }

    public void setHomeworkNote(String homeworkNote) {
        this.homeworkNote = homeworkNote;
    }

    public String getHomeworkImage() {
        return homeworkImage;
    }

    public void setHomeworkImage(String homeworkImage) {
        this.homeworkImage = homeworkImage;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getHomeworkDate() {
        return homeworkDate;
    }

    public void setHomeworkDate(String homeworkDate) {
        this.homeworkDate = homeworkDate;
    }




    ////  new /////

    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("event_description")
    @Expose
    private String eventDescription;
    @SerializedName("event_image")
    @Expose
    private String eventImage;
    @SerializedName("event_date")
    @Expose
    private String eventDate;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    @SerializedName("assignment_id")
    @Expose
    private String assignmentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pdf_file")
    @Expose
    private String pdfFile;

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }
    @SerializedName("toppers_id")
    @Expose
    private String toppersId;

    @SerializedName("standard")
    @Expose
    private String standard;

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("percentage")
    @Expose
    private String percentage;


    public String getToppersId() {
        return toppersId;
    }

    public void setToppersId(String toppersId) {
        this.toppersId = toppersId;
    }


    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

}

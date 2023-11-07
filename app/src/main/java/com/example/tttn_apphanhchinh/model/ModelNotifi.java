package com.example.tttn_apphanhchinh.model;

public class ModelNotifi {
    String idNews, idName, idDate, idTime;

    public ModelNotifi(String idNews, String idName, String idDate, String idTime) {
        this.idNews = idNews;
        this.idName = idName;
        this.idDate = idDate;
        this.idTime = idTime;
    }

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdDate() {
        return idDate;
    }

    public void setIdDate(String idDate) {
        this.idDate = idDate;
    }

    public String getIdTime() {
        return idTime;
    }

    public void setIdTime(String idTime) {
        this.idTime = idTime;
    }
}

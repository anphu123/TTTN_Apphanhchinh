package com.example.tttn_apphanhchinh.model;

public class ModelToa {
    String toa;
    String id;
    public ModelToa() {
        // Constructor mặc định không có tham số
    }
    public ModelToa(String toa, String id) {
        this.toa = toa;
        this.id = id;
    }

    public String getToa() {
        return toa;
    }

    public void setToa(String toa) {
        this.toa = toa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

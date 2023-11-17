package com.example.tttn_apphanhchinh.model;

public class ModelTang {
    private String Toa; // Make sure the names match the fields in your database
    private String Tang;

    public ModelTang() {
        // Default constructor required for Firebase
    }

    public ModelTang(String toa, String tang) {
        Toa = toa;
        Tang = tang;
    }

    public String getToa() {
        return Toa;
    }

    public void setToa(String toa) {
        Toa = toa;
    }

    public String getTang() {
        return Tang;
    }

    public void setTang(String tang) {
        Tang = tang;
    }
}

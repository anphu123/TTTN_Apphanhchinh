package com.example.tttn_apphanhchinh.model;

public class ModelNv2 {
    private String name;
    private String data;
    private String time;
    private String room;

    public ModelNv2(String name, String data, String time, String room) {
        this.name = name;
        this.data = data;
        this.time = time;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

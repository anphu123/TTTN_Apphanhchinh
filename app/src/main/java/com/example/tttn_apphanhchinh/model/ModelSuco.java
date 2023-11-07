package com.example.tttn_apphanhchinh.model;

public class ModelSuco {
    String name, toa, room, data, time;

    public ModelSuco(String name, String toa, String room, String data, String time) {
        this.name = name;
        this.toa = toa;
        this.room = room;
        this.data = data;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToa() {
        return toa;
    }

    public void setToa(String toa) {
        this.toa = toa;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
}

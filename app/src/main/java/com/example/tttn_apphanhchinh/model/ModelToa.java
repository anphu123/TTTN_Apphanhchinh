package com.example.tttn_apphanhchinh.model;

public class ModelToa {
    private String id;
    private String toa;
    private long timestamp;

    public ModelToa() {
        // Empty constructor needed for Firebase
    }

    public ModelToa(String id, String toa, long timestamp) {
        this.id = id;
        this.toa = toa;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getToa() {
        return toa;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

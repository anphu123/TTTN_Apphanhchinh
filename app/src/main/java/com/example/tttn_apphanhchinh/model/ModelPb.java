package com.example.tttn_apphanhchinh.model;

public class ModelPb {
    private String name;
    private String id;
    private String headName;
    private String phoneNumber;

    public ModelPb() {

    }

    public ModelPb(String name, String id, String headName, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.headName = headName;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

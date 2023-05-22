package com.darkness.sparkwomen;

public class DataClass {
    private String name;
    private String contact;
    private String address;
    private String description;
    private String image;

    public DataClass(String name, String contact, String address, String description, String image) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public DataClass(){

    }
}

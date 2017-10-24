package com.aslepakurov.geocoding.caller.model;

public class SearchRequest {
    private String address;

    public SearchRequest() {
    }

    public SearchRequest(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

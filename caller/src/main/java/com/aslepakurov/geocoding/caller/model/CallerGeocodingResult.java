package com.aslepakurov.geocoding.caller.model;

public class CallerGeocodingResult {
    private String address;
    private double lat;
    private double lon;
    private String formattedAddress;

    public CallerGeocodingResult() {
    }

    public CallerGeocodingResult(String address, double lat, double lon, String formattedAddress) {
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}

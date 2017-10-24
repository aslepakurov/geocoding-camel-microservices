package com.aslepakurov.geocoding.caller.model;

import com.google.maps.model.Geometry;

import javax.xml.bind.annotation.XmlElement;

public class SimpleGeocodingResult {
    private String formatted_address;
    private Geometry geometry;

    public SimpleGeocodingResult() {
    }

    public SimpleGeocodingResult(String formatted_address, Geometry geometry) {
        this.formatted_address = formatted_address;
        this.geometry = geometry;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
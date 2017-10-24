package com.aslepakurov.geocoding.caller.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GeocodeResponse")
public class GeocodingResultContainer {
    private SimpleGeocodingResult[] result;

    public GeocodingResultContainer() {
    }

    public GeocodingResultContainer(SimpleGeocodingResult[] result) {
        this.result = result;
    }

    public SimpleGeocodingResult[] getResult() {
        return result;
    }

    public void setResult(SimpleGeocodingResult[] result) {
        this.result = result;
    }
}

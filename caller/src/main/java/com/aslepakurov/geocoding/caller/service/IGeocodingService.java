package com.aslepakurov.geocoding.caller.service;

import com.aslepakurov.geocoding.caller.model.CallerGeocodingResult;
import com.aslepakurov.geocoding.caller.model.SearchRequest;
import com.google.maps.errors.ApiException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IGeocodingService {
    CallerGeocodingResult getGeocodingAPI(SearchRequest request) throws InterruptedException, ApiException, IOException;
    CallerGeocodingResult getGeocodingURL(SearchRequest request) throws IOException, JAXBException;
}

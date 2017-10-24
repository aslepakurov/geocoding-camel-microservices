package com.aslepakurov.geocoding.caller.service;

import com.aslepakurov.geocoding.caller.model.CallerGeocodingResult;
import com.aslepakurov.geocoding.caller.model.GeocodingResultContainer;
import com.aslepakurov.geocoding.caller.model.SearchRequest;
import com.aslepakurov.geocoding.caller.model.SimpleGeocodingResult;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

@Service
public class GeocodingService implements IGeocodingService {
    private final GeoApiContext geoApiContext;
    private final OkHttpClient httpClient;

    @Value("${google.api.basicUrl}")
    private String googleApiBasicUrl;

    @Autowired
    public GeocodingService(GeoApiContext geoApiContext, OkHttpClient httpClient) {
        this.geoApiContext = geoApiContext;
        this.httpClient = httpClient;
    }

    /**
     * Get first result when searching Google Geocoding API
     *
     * @param request string with search parameters
     * @return first result from Geocoding API
     */
    public CallerGeocodingResult getGeocodingAPI(SearchRequest request) throws InterruptedException, ApiException, IOException {
        GeocodingResult[] geocodingResults = GeocodingApi
                .geocode(geoApiContext, request.getAddress())
                .await();
        return getFirstResult(request, geocodingResults);
    }

    public CallerGeocodingResult getGeocodingURL(SearchRequest request) throws IOException, JAXBException {
        Response response = httpClient
                .newCall(new Request.Builder()
                        .url(String.format(googleApiBasicUrl, request.getAddress()))
                        .build())
                .execute();
        ResponseBody body = response.body();
        if (body == null) return null;
        SimpleGeocodingResult[] geocodingResults = jaxbXMLToObject(body.string());
        return getFirstResult(request, geocodingResults);
    }

    private CallerGeocodingResult buildResult(String address, String formattedAdress, double lat, double lon) {
        CallerGeocodingResult callerGeocodingResult = new CallerGeocodingResult();
        callerGeocodingResult.setAddress(address);
        callerGeocodingResult.setFormattedAddress(formattedAdress);
        callerGeocodingResult.setLat(lat);
        callerGeocodingResult.setLon(lon);
        return callerGeocodingResult;
    }

    private static SimpleGeocodingResult[] jaxbXMLToObject(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(GeocodingResultContainer.class);
        Unmarshaller un = context.createUnmarshaller();
        GeocodingResultContainer container = (GeocodingResultContainer) un.unmarshal(new StringReader(xml));
        return container.getResult();
    }

    private CallerGeocodingResult getFirstResult(SearchRequest request, GeocodingResult[] geocodingResults) {
        if (geocodingResults.length < 1) return null;
        GeocodingResult returnGeocodingResult = geocodingResults[0];
        return buildResult(request.getAddress(),
                returnGeocodingResult.formattedAddress,
                returnGeocodingResult.geometry.location.lat,
                returnGeocodingResult.geometry.location.lng);
    }

    private CallerGeocodingResult getFirstResult(SearchRequest request, SimpleGeocodingResult[] geocodingResults) {
        if (geocodingResults.length < 1) return null;
        SimpleGeocodingResult returnGeocodingResult = geocodingResults[0];
        return buildResult(request.getAddress(),
                returnGeocodingResult.getFormatted_address(),
                returnGeocodingResult.getGeometry().location.lat,
                returnGeocodingResult.getGeometry().location.lng);
    }
}

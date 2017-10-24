package com.aslepakurov.geocoding.caller.routes;

import com.aslepakurov.geocoding.caller.model.CallerGeocodingResult;
import com.aslepakurov.geocoding.caller.model.SearchRequest;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CallerRoute extends RouteBuilder {

    @Value("${server.port}")
    private int port;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("netty-http")
                .bindingMode(RestBindingMode.json)
                .port(port)
                .dataFormatProperty("prettyPrint", "true")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Geocoding Camel Microservices API").apiProperty("api.version", "0.1")
                .apiProperty("cors", "true");

        rest("/callerAPI")
                .post()
                .description("Geocoding search with Google API via Java library")
                .outType(CallerGeocodingResult.class)
                .param().name("id").type(RestParamType.body).description("Search string for Geocoding API").dataType("String").endParam()
                .consumes("application/json").produces("application/json").type(SearchRequest.class)
                .to("bean:geocodingService?method=getGeocodingAPI(${body})");

        rest("/callerURL")
                .post()
                .description("Geocoding search with Google API via URL")
                .outType(CallerGeocodingResult.class)
                .param().name("id").type(RestParamType.body).description("Search string for Geocoding API").dataType("String").endParam()
                .consumes("application/json").produces("application/json").type(SearchRequest.class)
                .to("bean:geocodingService?method=getGeocodingURL(${body})");
    }
}

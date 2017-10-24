package com.aslepakurov.geocoding.caller;

import com.google.maps.GeoApiContext;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelCallerService {

    @Value("${google.api.key}")
    private String googleApiKey;

    public static void main(String[] args) {
        SpringApplication.run(CamelCallerService.class, args);
    }

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(googleApiKey)
                .build();
    }

    @Bean
    public OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}

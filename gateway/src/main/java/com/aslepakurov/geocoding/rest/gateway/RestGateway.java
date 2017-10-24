package com.aslepakurov.geocoding.rest.gateway;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestGateway extends RouteBuilder {

    @Value("${server.port}")
    private int port;

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("netty4-http")
            .bindingMode(RestBindingMode.json)
            .port(port);

        from("rest:post:callerAPI:/")
                .serviceCall("caller");

        from("rest:post:callerURL:/")
                .serviceCall("caller");
    }
}

package com.garofalo.fearladderhealthcheck.api;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.ObjectProvider;

import java.time.Instant;
import java.util.Optional;

@RestController
public class PingController {

    private final String version;

    public PingController(ObjectProvider<BuildProperties> buildProperties) {
        this.version = Optional.ofNullable(buildProperties.getIfAvailable())
                .map(BuildProperties::getVersion)
                .orElse("dev");
    }

    public record PingResponse(String version, String status, Instant timestamp) {}

    @GetMapping("api/v1/ping")
    public PingResponse ping() {
        return new PingResponse("pong", version, Instant.now());
    }

}

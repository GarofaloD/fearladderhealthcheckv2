package com.garofalo.fearladderhealthcheck.api;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class PingController {

    private final BuildProperties buildProperties;


    public PingController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public record PingResponse(String version, String status, Instant timestamp) {}

    @GetMapping("api/v1/ping")
    public PingResponse ping() {
        return new PingResponse("pong", buildProperties.getVersion(), Instant.now());
    }

}

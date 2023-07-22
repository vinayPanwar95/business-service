package com.learning.businessservice.metrics;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.learning.metrics")
@Data
public class MetricsProperties {
    private String meterWhiteList;
    private int logPublishIntervalInSeconds;
}

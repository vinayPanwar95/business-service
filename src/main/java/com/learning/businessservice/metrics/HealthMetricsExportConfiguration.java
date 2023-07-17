package com.learning.businessservice.metrics;//package com.learning.businessservice.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class HealthMetricsExportConfiguration {
    private static final String HEALTH_METRIC_NAME = "health";


    public HealthMetricsExportConfiguration(HealthEndpoint healthEndPoint , MeterRegistry meterRegistry ){
        Gauge.builder(HEALTH_METRIC_NAME, healthEndPoint, this::getStatusCode).strongReference(true).register(meterRegistry);
    }

    private int getStatusCode(HealthEndpoint healthEndPoint){
        Status healthStatus = healthEndPoint.health().getStatus();
        if(Status.UP.equals(healthStatus)){
            return 3;
        }
        if(Status.OUT_OF_SERVICE.equals(healthStatus)){
            return 2;
        }
        if(Status.DOWN.equals(healthStatus)){
            return 1;
        }
        return 0;
    }
}

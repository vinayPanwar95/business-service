
package com.learning.businessservice.metrics;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@ConditionalOnProperty(prefix = "com.learning", name = "enabled" , matchIfMissing = true)
@Configuration
@EnableConfigurationProperties(MetricsProperties.class)
@Import(HealthMetricsExportConfiguration.class)
@Slf4j
public class MetricsAutoConfiguration {
     private static final String ALL_METER_WILD_CARD = "*";

     private final Integer logPublishIntervalInSeconds;

     private Set<String> meterWhiteList = Collections.emptySet();

     private boolean showAllMeters;

     public MetricsAutoConfiguration(MetricsProperties metricsProperties){
//         this.logPublishIntervalInSeconds = metricsProperties.getLogPublishIntervalInSeconds();
         this.logPublishIntervalInSeconds = 2;
         String metersWhiteList = metricsProperties.getMeterWhiteList();
         if(metersWhiteList!= null){
             showAllMeters= metersWhiteList.trim().equals(ALL_METER_WILD_CARD);
             if(!showAllMeters){
                 this.meterWhiteList = Arrays.stream(metricsProperties.getMeterWhiteList().split(",")).map(String::trim).collect(Collectors.toUnmodifiableSet());
             }
         }
     }
     @Bean
    @Primary
    public MeterRegistry loggingMeterRegistry(){
         LoggingRegistryConfig loggingRegistryConfig = createLoggingRegistryConfig(logPublishIntervalInSeconds);
         LoggingMeterRegistry loggingMeterRegistry = new LoggingMeterRegistry(loggingRegistryConfig, Clock.SYSTEM);

         MeterFilter meterFilter;
         if(showAllMeters){
             log.info("All meters will be accepted in meter registry");
             meterFilter = MeterFilter.accept();
         }
         else{
             log.info("Following metes are whiteListed  : {} and will be accepted in meter registry", meterWhiteList);
             meterFilter = MeterFilter.denyUnless(meterId -> meterWhiteList.contains(meterId.getName()));
         }
         loggingMeterRegistry.config().meterFilter(meterFilter);

         return loggingMeterRegistry;
     }



     @Bean
     public TimedAspect timedAspect(MeterRegistry meterRegistry){
         return new TimedAspect(meterRegistry);
     }

    private LoggingRegistryConfig createLoggingRegistryConfig(Integer publishInterval) {
         return new LoggingRegistryConfig() {
             @Override
             public String get(@NonNull  String key) {
                 return null;
             }

             @Override
             public @NonNull Duration step(){
                 return Duration.ofSeconds(publishInterval);
             }
         };
    }

}


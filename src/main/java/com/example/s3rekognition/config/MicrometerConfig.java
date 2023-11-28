package com.example.s3rekognition.config;

import io.micrometer.cloudwatch2.CloudWatchConfig;
import io.micrometer.cloudwatch2.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;

public class MicrometerConfig {
  
  public MeterRegistry meterRegistry() {
    CloudWatchConfig cloudWatchConfig = new CloudWatchConfig() {
      @Override
      public String get(String key) {
        return null; // Using default config
      }

      @Override
      public String namespace() {
        return "candidate-2042-metrics";
      }
    };
    
    CloudWatchAsyncClient cloudWatchAsyncClient = CloudWatchAsyncClient.create();
    
    return new CloudWatchMeterRegistry(cloudWatchConfig, Clock.SYSTEM, cloudWatchAsyncClient);
  }
}

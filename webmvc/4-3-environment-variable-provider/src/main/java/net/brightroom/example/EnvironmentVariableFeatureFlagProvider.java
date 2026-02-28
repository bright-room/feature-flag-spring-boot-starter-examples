package net.brightroom.example;

import net.brightroom.featureflag.webmvc.provider.FeatureFlagProvider;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentVariableFeatureFlagProvider implements FeatureFlagProvider {

  @Override
  public boolean isFeatureEnabled(String featureName) {
    String envKey = "FF_" + featureName.toUpperCase().replace('-', '_');
    return "true".equalsIgnoreCase(System.getenv(envKey));
  }
}

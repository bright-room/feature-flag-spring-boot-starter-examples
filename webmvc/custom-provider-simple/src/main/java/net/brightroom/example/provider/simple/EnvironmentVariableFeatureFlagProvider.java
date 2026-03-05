package net.brightroom.example.provider.simple;

import net.brightroom.featureflag.core.provider.FeatureFlagProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("env-variable")
public class EnvironmentVariableFeatureFlagProvider implements FeatureFlagProvider {

  @Override
  public boolean isFeatureEnabled(String featureName) {
    String envKey = "FF_" + featureName.toUpperCase().replace('-', '_');
    return "true".equalsIgnoreCase(System.getenv(envKey));
  }
}

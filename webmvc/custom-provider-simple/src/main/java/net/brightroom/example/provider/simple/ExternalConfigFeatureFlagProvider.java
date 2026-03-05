package net.brightroom.example.provider.simple;

import net.brightroom.featureflag.core.provider.FeatureFlagProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("external-config")
@EnableConfigurationProperties(ExternalConfigProperties.class)
public class ExternalConfigFeatureFlagProvider implements FeatureFlagProvider {

  private final ExternalConfigProperties properties;

  public ExternalConfigFeatureFlagProvider(ExternalConfigProperties properties) {
    this.properties = properties;
  }

  @Override
  public boolean isFeatureEnabled(String featureName) {
    return Boolean.TRUE.equals(properties.getFlags().get(featureName));
  }
}

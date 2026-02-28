package net.brightroom.example;

import net.brightroom.featureflag.webmvc.provider.FeatureFlagProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
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

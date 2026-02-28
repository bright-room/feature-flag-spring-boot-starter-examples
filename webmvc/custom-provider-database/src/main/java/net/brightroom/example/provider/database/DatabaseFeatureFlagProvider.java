package net.brightroom.example.provider.database;

import net.brightroom.featureflag.webmvc.provider.FeatureFlagProvider;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFeatureFlagProvider implements FeatureFlagProvider {

  private final FeatureManagementMapper mapper;

  public DatabaseFeatureFlagProvider(FeatureManagementMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public boolean isFeatureEnabled(String featureName) {
    Boolean enabled = mapper.findEnabledByFeatureName(featureName);
    return Boolean.TRUE.equals(enabled);
  }
}

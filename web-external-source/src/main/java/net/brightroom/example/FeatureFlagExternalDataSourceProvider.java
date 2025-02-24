package net.brightroom.example;

import net.brightroom.featureflag.provider.FeatureFlagProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
class FeatureFlagExternalDataSourceProvider implements FeatureFlagProvider {

  FeatureManagementMapper featureManagementMapper;

  @Override
  public boolean isFeatureEnabled(String featureName) {
    Boolean enabled = featureManagementMapper.check(featureName);
    if (enabled == null) return true;
    return enabled;
  }

  FeatureFlagExternalDataSourceProvider(FeatureManagementMapper featureManagementMapper) {
    this.featureManagementMapper = featureManagementMapper;
  }
}

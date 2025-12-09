package net.brightroom.example;

import java.util.Objects;
import net.brightroom.featureflag.provider.FeatureFlagProvider;
import org.springframework.stereotype.Component;

@Component
class FeatureFlagExternalDataSourceProvider implements FeatureFlagProvider {

  FeatureManagementMapper featureManagementMapper;

  @Override
  public boolean isFeatureEnabled(String featureName) {
    Boolean enabled = featureManagementMapper.check(featureName);
    if (Objects.isNull(enabled)) return true;
    return enabled;
  }

  FeatureFlagExternalDataSourceProvider(FeatureManagementMapper featureManagementMapper) {
    this.featureManagementMapper = featureManagementMapper;
  }
}

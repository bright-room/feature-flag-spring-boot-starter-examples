package net.brightroom.example;

import java.util.Objects;
import net.brightroom.featureflag.provider.FeatureFlagProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
class FeatureFlagExternalDataSourceProvider implements FeatureFlagProvider {

  FeatureManagementRepository featureManagementRepository;

  @Override
  public Mono<Boolean> isFeatureEnabled(String featureName) {
    return featureManagementRepository
            .findById(featureName)
            .defaultIfEmpty(new FeatureManagement(featureName, true))
            .map(FeatureManagement::enabled);
  }

  FeatureFlagExternalDataSourceProvider(FeatureManagementRepository featureManagementRepository) {
    this.featureManagementRepository = featureManagementRepository;
  }
}

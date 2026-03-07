package net.brightroom.example.customprovider;

import java.util.Map;
import net.brightroom.featureflag.core.provider.ReactiveFeatureFlagProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@EnableConfigurationProperties(ExternalConfigProperties.class)
public class ExternalConfigReactiveFeatureFlagProvider implements ReactiveFeatureFlagProvider {

  private final Map<String, Boolean> flags;

  public ExternalConfigReactiveFeatureFlagProvider(ExternalConfigProperties properties) {
    this.flags = Map.copyOf(properties.getFlags());
  }

  @Override
  public Mono<Boolean> isFeatureEnabled(String featureName) {
    Boolean enabled = flags.get(featureName);
    if (enabled == null) {
      return Mono.empty();
    }
    return Mono.just(enabled);
  }
}

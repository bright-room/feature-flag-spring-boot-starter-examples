package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.context.FeatureFlagContext;
import net.brightroom.featureflag.webflux.context.ReactiveFeatureFlagContextResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Profile("sticky")
public class UserIdReactiveContextResolver implements ReactiveFeatureFlagContextResolver {

  @Override
  public Mono<FeatureFlagContext> resolve(ServerHttpRequest request) {
    String userId = request.getQueryParams().getFirst("userId");
    if (userId == null || userId.isBlank()) {
      return Mono.empty();
    }
    return Mono.just(new FeatureFlagContext(userId));
  }
}

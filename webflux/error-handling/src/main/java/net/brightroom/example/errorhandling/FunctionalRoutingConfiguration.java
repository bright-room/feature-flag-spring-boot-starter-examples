package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.webflux.filter.FeatureFlagHandlerFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Profile("functional-error")
public class FunctionalRoutingConfiguration {

  private final FeatureFlagHandlerFilterFunction featureFlagFilter;

  @Bean
  public RouterFunction<ServerResponse> functionalRoutes() {
    return RouterFunctions.route()
        .GET(
            "/functional/premium",
            req -> ServerResponse.ok().bodyValue("Functional premium feature is available!"))
        .filter(featureFlagFilter.of("premium-feature"))
        .build();
  }

  public FunctionalRoutingConfiguration(FeatureFlagHandlerFilterFunction featureFlagFilter) {
    this.featureFlagFilter = featureFlagFilter;
  }
}

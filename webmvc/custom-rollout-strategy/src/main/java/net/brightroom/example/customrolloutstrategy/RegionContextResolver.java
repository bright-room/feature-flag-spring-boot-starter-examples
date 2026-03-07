package net.brightroom.example.customrolloutstrategy;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import net.brightroom.featureflag.core.context.FeatureFlagContext;
import net.brightroom.featureflag.webmvc.context.FeatureFlagContextResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("region")
public class RegionContextResolver implements FeatureFlagContextResolver {

  @Override
  public Optional<FeatureFlagContext> resolve(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader("X-Region"))
        .filter(region -> !region.isBlank())
        .map(FeatureFlagContext::new);
  }
}

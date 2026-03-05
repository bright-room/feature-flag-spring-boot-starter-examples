package net.brightroom.example.rollout;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import net.brightroom.featureflag.core.context.FeatureFlagContext;
import net.brightroom.featureflag.webmvc.context.FeatureFlagContextResolver;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("sticky")
public class UserIdContextResolver implements FeatureFlagContextResolver {

  @Override
  public Optional<FeatureFlagContext> resolve(HttpServletRequest request) {
    return Optional.ofNullable(request.getParameter("userId"))
        .filter(id -> !id.isBlank())
        .map(FeatureFlagContext::new);
  }
}

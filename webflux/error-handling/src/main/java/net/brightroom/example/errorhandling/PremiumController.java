package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PremiumController {

  @GetMapping("/premium")
  @FeatureFlag("premium-feature")
  public Mono<String> premium() {
    return Mono.just("Premium feature is available!");
  }

  @GetMapping("/coming-soon")
  public Mono<String> comingSoon() {
    return Mono.just("This feature is coming soon. Stay tuned!");
  }
}

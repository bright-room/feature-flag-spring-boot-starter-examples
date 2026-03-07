package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StickyRolloutController {

  @FeatureFlag(value = "sticky-rollout", rollout = 50)
  @GetMapping("/api/sticky-rollout")
  public Mono<String> stickyRollout() {
    return Mono.just("sticky-rollout: you are in the rollout group!");
  }
}

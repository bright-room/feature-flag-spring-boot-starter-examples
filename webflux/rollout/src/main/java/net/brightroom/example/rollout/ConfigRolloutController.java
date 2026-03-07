package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ConfigRolloutController {

  @FeatureFlag("config-rollout")
  @GetMapping("/api/config-rollout")
  public Mono<String> configRollout() {
    return Mono.just("config-rollout: you are in the rollout group!");
  }
}

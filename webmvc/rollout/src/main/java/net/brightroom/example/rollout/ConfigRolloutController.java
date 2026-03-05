package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigRolloutController {

  @GetMapping("/api/config-rollout")
  @FeatureFlag("config-rollout")
  public String configRollout() {
    return "config-rollout: you are in the rollout group!";
  }
}

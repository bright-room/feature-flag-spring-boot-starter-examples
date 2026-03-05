package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StickyRolloutController {

  @GetMapping("/api/sticky-rollout")
  @FeatureFlag(value = "sticky-rollout", rollout = 50)
  public String stickyRollout() {
    return "sticky-rollout: you are in the rollout group!";
  }
}

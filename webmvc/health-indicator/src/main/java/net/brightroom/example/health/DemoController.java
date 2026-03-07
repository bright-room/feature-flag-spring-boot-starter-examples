package net.brightroom.example.health;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @GetMapping("/api/active")
  @FeatureFlag("active-feature")
  public String active() {
    return "active-feature is enabled!";
  }

  @GetMapping("/api/inactive")
  @FeatureFlag("inactive-feature")
  public String inactive() {
    return "inactive-feature is enabled!";
  }

  @GetMapping("/api/rollout")
  @FeatureFlag("rollout-feature")
  public String rollout() {
    return "rollout-feature is enabled!";
  }
}

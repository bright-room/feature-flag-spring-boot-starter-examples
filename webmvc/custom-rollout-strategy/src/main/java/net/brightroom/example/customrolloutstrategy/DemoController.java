package net.brightroom.example.customrolloutstrategy;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @GetMapping("/api/gradual")
  @FeatureFlag(value = "gradual-feature", rollout = 60)
  public String gradual() {
    return "You are in the rollout group for gradual-feature!";
  }
}

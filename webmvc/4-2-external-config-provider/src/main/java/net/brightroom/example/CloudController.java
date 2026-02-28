package net.brightroom.example;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CloudController {

  @GetMapping("/cloud")
  @FeatureFlag("cloud-feature")
  public String cloud() {
    return "Cloud feature is enabled";
  }

  @GetMapping("/beta")
  @FeatureFlag("beta-feature")
  public String beta() {
    return "Beta feature is enabled";
  }
}

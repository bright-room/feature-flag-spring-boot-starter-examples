package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PremiumController {

  @GetMapping("/premium")
  @FeatureFlag("premium-feature")
  public String premium() {
    return "Premium feature is enabled";
  }
}

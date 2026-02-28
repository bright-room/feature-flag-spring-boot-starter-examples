package net.brightroom.example;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

  @GetMapping("/dashboard")
  @FeatureFlag("new-dashboard")
  public String dashboard() {
    return "New dashboard is enabled";
  }
}

package net.brightroom.example.provider.database;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExperimentalController {

  @GetMapping("/experimental")
  @FeatureFlag("experimental")
  public String experimental() {
    return "Experimental feature is enabled";
  }
}

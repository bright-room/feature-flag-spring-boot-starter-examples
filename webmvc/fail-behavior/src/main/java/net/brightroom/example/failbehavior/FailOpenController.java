package net.brightroom.example.failbehavior;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fail-open")
public class FailOpenController {

  @GetMapping("/known-disabled")
  @FeatureFlag("known-disabled")
  public String knownDisabled() {
    return "This should not be reached (explicitly disabled)";
  }

  @GetMapping("/unknown")
  @FeatureFlag("undefined-feature")
  public String unknown() {
    return "Undefined feature is allowed (fail-open)";
  }
}

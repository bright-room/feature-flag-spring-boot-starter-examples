package net.brightroom.example.failbehavior;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fail-closed")
public class FailClosedController {

  @GetMapping("/known")
  @FeatureFlag("known-feature")
  public String known() {
    return "Known feature is enabled";
  }

  @GetMapping("/unknown")
  @FeatureFlag("undefined-feature")
  public String unknown() {
    return "This should not be reached (fail-closed)";
  }
}

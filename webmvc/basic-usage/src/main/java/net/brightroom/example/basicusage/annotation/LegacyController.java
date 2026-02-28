package net.brightroom.example.basicusage.annotation;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legacy")
@FeatureFlag("legacy-api")
public class LegacyController {

  @GetMapping("/data")
  public String data() {
    return "Legacy data";
  }

  @GetMapping("/special")
  @FeatureFlag("special-endpoint")
  public String special() {
    return "Special endpoint data";
  }
}

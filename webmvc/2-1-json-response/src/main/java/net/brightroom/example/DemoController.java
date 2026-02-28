package net.brightroom.example;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

  @GetMapping("/demo")
  @FeatureFlag("demo-feature")
  public String demo() {
    return "Demo feature response";
  }
}

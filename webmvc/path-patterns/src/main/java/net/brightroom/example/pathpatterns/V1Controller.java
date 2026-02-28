package net.brightroom.example.pathpatterns;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class V1Controller {

  @GetMapping("/data")
  @FeatureFlag("v2-api")
  public String data() {
    return "V1 data (always accessible - outside include pattern)";
  }
}

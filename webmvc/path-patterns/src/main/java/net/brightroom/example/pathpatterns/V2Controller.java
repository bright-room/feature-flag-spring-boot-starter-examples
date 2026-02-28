package net.brightroom.example.pathpatterns;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class V2Controller {

  @GetMapping("/data")
  @FeatureFlag("v2-api")
  public String data() {
    return "V2 data";
  }
}

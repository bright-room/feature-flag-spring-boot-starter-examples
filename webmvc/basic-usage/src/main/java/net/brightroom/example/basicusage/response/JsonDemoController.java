package net.brightroom.example.basicusage.response;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class JsonDemoController {

  @GetMapping("/json")
  @FeatureFlag("json-demo")
  public String json() {
    return "JSON demo response";
  }
}

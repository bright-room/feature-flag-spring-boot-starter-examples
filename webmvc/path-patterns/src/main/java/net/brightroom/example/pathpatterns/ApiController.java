package net.brightroom.example.pathpatterns;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@FeatureFlag("api-feature")
public class ApiController {

  @GetMapping("/data")
  public String data() {
    return "API data";
  }

  @GetMapping("/health")
  public String health() {
    return "OK";
  }

  @GetMapping("/info")
  public String info() {
    return "Application info";
  }
}

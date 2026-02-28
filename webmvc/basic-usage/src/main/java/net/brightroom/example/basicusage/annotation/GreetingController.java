package net.brightroom.example.basicusage.annotation;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeatureFlag("greeting")
public class GreetingController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello, World!";
  }

  @GetMapping("/goodbye")
  public String goodbye() {
    return "Goodbye, World!";
  }
}

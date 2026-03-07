package net.brightroom.example.actuator;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

  @GetMapping("/api/demo")
  @FeatureFlag("demo-feature")
  public Mono<String> demo() {
    return Mono.just("demo-feature is enabled!");
  }

  @GetMapping("/api/another")
  @FeatureFlag("another-feature")
  public Mono<String> another() {
    return Mono.just("another-feature is enabled!");
  }
}

package net.brightroom.example.healthindicator;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class DemoController {

  @GetMapping("/active")
  @FeatureFlag("active-feature")
  public Mono<String> active() {
    return Mono.just("active-feature is enabled!");
  }

  @GetMapping("/inactive")
  @FeatureFlag("inactive-feature")
  public Mono<String> inactive() {
    return Mono.just("inactive-feature is enabled!");
  }
}

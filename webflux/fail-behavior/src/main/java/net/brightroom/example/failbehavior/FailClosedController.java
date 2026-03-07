package net.brightroom.example.failbehavior;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fail-closed")
public class FailClosedController {

  @GetMapping("/known")
  @FeatureFlag("known-feature")
  public Mono<String> known() {
    return Mono.just("Known feature is enabled");
  }

  @GetMapping("/unknown")
  @FeatureFlag("undefined-feature")
  public Mono<String> unknown() {
    return Mono.just("This should not be reached (fail-closed)");
  }
}

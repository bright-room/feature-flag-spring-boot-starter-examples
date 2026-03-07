package net.brightroom.example.failbehavior;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fail-open")
public class FailOpenController {

  @GetMapping("/known-disabled")
  @FeatureFlag("known-disabled")
  public Mono<String> knownDisabled() {
    return Mono.just("This should not be reached (explicitly disabled)");
  }

  @GetMapping("/unknown")
  @FeatureFlag("undefined-feature")
  public Mono<String> unknown() {
    return Mono.just("Undefined feature is allowed (fail-open)");
  }
}

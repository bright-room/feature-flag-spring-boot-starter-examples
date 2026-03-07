package net.brightroom.example.basicusage;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/legacy")
@FeatureFlag("legacy-api")
public class LegacyController {

  @GetMapping("/data")
  public Mono<String> data() {
    return Mono.just("Legacy data");
  }

  @FeatureFlag("special-endpoint")
  @GetMapping("/special")
  public Mono<String> special() {
    return Mono.just("Special endpoint (method-level overrides class-level)");
  }
}

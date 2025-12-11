package net.brightroom.example;

import net.brightroom.featureflag.annotation.FeatureFlag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
class DevelopmentController {

  @FeatureFlag("development")
  @GetMapping("/development")
  Mono<String> development() {
    return Mono.just("development");
  }
}

package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AnnotationRolloutController {

  @FeatureFlag(value = "annotation-rollout", rollout = 50)
  @GetMapping("/api/annotation-rollout")
  public Mono<String> rollout() {
    return Mono.just("annotation-rollout: you are in the 50% rollout group!");
  }
}

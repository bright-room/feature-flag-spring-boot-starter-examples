package net.brightroom.example.rollout;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationRolloutController {

  @GetMapping("/api/annotation-rollout")
  @FeatureFlag(value = "annotation-rollout", rollout = 50)
  public String annotationRollout() {
    return "annotation-rollout: you are in the rollout group!";
  }
}

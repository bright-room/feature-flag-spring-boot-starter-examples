package net.brightroom.example;

import net.brightroom.featureflag.annotation.FeatureFlag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class DevelopmentController {

  @FeatureFlag("development")
  @GetMapping("/development")
  String development() {
    return "development";
  }
}

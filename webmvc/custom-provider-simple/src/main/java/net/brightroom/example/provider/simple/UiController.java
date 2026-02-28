package net.brightroom.example.provider.simple;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ui")
public class UiController {

  @GetMapping("/dark-mode")
  @FeatureFlag("dark-mode")
  public String darkMode() {
    return "Dark mode is enabled";
  }
}

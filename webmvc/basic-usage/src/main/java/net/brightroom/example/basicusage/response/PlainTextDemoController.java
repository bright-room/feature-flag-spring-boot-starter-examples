package net.brightroom.example.basicusage.response;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class PlainTextDemoController {

  @GetMapping("/plain-text")
  @FeatureFlag("plain-text-demo")
  public String plainText() {
    return "Plain text demo response";
  }
}

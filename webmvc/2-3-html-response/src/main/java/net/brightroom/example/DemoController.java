package net.brightroom.example;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

  @GetMapping(value = "/demo", produces = MediaType.TEXT_HTML_VALUE)
  @FeatureFlag("demo-feature")
  public String demo() {
    return "<html><body><h1>Demo feature response</h1></body></html>";
  }
}

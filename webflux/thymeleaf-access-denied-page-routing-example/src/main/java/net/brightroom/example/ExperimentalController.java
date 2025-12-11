package net.brightroom.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
class ExperimentalController {

  @GetMapping("/experimental")
  Mono<String> experimental() {
    return Mono.just("experimental");
  }
}

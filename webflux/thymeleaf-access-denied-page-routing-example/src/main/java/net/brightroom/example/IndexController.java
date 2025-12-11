package net.brightroom.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
class IndexController {
  @GetMapping("/")
  Mono<String> index() {
    return Mono.just("index");
  }
}

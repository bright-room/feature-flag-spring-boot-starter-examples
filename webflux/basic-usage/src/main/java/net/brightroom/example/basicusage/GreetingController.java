package net.brightroom.example.basicusage;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello")
@FeatureFlag("greeting")
public class GreetingController {

  @GetMapping
  public Mono<String> hello() {
    return Mono.just("Hello, World!");
  }

  @GetMapping("/stream")
  public Flux<String> helloStream() {
    return Flux.just("Hello", "World", "!");
  }
}

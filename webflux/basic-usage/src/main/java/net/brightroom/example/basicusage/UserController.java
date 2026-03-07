package net.brightroom.example.basicusage;

import net.brightroom.featureflag.core.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

  @FeatureFlag("new-search")
  @GetMapping("/search")
  public Mono<String> search() {
    return Mono.just("Search result");
  }

  @FeatureFlag("new-export")
  @GetMapping("/export")
  public Flux<String> export() {
    return Flux.just("row1", "row2", "row3");
  }

  @GetMapping("/list")
  public Mono<String> list() {
    return Mono.just("User list (no feature flag)");
  }
}

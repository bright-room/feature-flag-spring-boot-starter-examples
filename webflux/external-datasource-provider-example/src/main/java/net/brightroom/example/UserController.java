package net.brightroom.example;

import net.brightroom.featureflag.annotation.FeatureFlag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@FeatureFlag("development")
@RestController
@RequestMapping("/api/v2/user")
class UserController {

  @GetMapping
  Mono<String> get(@RequestParam("id") String id) {
    return Mono.just(id);
  }

  UserController() {}
}

package net.brightroom.example;

import net.brightroom.featureflag.annotation.FeatureFlag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/todo")
class LegacyTodoController {
  @GetMapping
  Mono<String> get(@RequestParam("id") String id) {
    return Mono.just(id);
  }

  @FeatureFlag("experimental")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Mono<Void> create() {
    return Mono.empty();
  }

  @FeatureFlag("development")
  @PutMapping(path = "/{id}")
  Mono<Void> modify(@PathVariable("id") String id) {
    return Mono.empty();
  }

  LegacyTodoController() {}
}

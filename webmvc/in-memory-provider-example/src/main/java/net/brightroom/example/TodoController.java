package net.brightroom.example;

import net.brightroom.featureflag.annotation.FeatureFlag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/todo")
class TodoController {
  @GetMapping
  String get(@RequestParam("id") String id) {
    return id;
  }

  @FeatureFlag("experimental")
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void create() {}

  @FeatureFlag("development")
  @PutMapping(path = "/{id}")
  void modify(@PathVariable("id") String id) {}

  TodoController() {}
}

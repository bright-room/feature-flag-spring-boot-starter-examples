package net.brightroom.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ExperimentalController {

  @GetMapping("/experimental")
  String experimental() {
    return "experimental";
  }
}

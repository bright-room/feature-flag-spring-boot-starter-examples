package net.brightroom.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AccessDeniedController {

  @GetMapping("/access-denied")
  String accessDenied() {
    return "access-denied";
  }
}

package net.brightroom.example.errorhandling;

import java.util.Map;
import net.brightroom.featureflag.core.exception.FeatureFlagAccessDeniedException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(0)
@Profile("json-error")
public class CustomExceptionHandler {

  @ExceptionHandler(FeatureFlagAccessDeniedException.class)
  public ResponseEntity<Map<String, Object>> handle(FeatureFlagAccessDeniedException e) {
    Map<String, Object> body =
        Map.of(
            "error", "Feature Unavailable",
            "feature", e.featureName(),
            "message", "This feature is currently disabled.");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
  }
}

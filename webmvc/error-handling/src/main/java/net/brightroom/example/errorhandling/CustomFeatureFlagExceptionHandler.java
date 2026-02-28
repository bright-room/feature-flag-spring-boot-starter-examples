package net.brightroom.example.errorhandling;

import java.util.Map;
import net.brightroom.featureflag.core.exception.FeatureFlagAccessDeniedException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(0)
@Profile("json-error")
public class CustomFeatureFlagExceptionHandler {

  @ExceptionHandler(FeatureFlagAccessDeniedException.class)
  public ResponseEntity<Map<String, String>> handleFeatureFlagAccessDenied(
      FeatureFlagAccessDeniedException ex) {
    return ResponseEntity.status(403)
        .body(
            Map.of(
                "error", "coming_soon",
                "feature", ex.featureName(),
                "message", "This feature is not yet available. Stay tuned!"));
  }
}

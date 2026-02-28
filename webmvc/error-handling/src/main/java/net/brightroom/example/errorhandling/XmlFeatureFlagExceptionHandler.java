package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.core.exception.FeatureFlagAccessDeniedException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@ControllerAdvice
@Order(0)
@Profile("xml-error")
public class XmlFeatureFlagExceptionHandler {

  @ExceptionHandler(FeatureFlagAccessDeniedException.class)
  public ResponseEntity<FeatureFlagError> handleFeatureFlagAccessDenied(
      FeatureFlagAccessDeniedException ex) {
    var error =
        new FeatureFlagError(
            "coming_soon", ex.featureName(), "This feature is not yet available. Stay tuned!");
    return ResponseEntity.status(403).contentType(MediaType.APPLICATION_XML).body(error);
  }

  @JacksonXmlRootElement(localName = "feature-flag-error")
  public record FeatureFlagError(String error, String feature, String message) {}
}

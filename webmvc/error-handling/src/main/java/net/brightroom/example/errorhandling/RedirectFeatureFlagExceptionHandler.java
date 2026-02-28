package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.core.exception.FeatureFlagAccessDeniedException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
@Order(0)
@Profile("redirect")
public class RedirectFeatureFlagExceptionHandler {

  @ExceptionHandler(FeatureFlagAccessDeniedException.class)
  public RedirectView handleFeatureFlagAccessDenied(FeatureFlagAccessDeniedException ex) {
    return new RedirectView("/coming-soon");
  }
}

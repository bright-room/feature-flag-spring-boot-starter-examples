package net.brightroom.example.errorhandling;

import net.brightroom.featureflag.core.exception.FeatureFlagAccessDeniedException;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Order(0)
@Profile("thymeleaf-error")
public class ThymeleafFeatureFlagExceptionHandler {

  @ExceptionHandler(FeatureFlagAccessDeniedException.class)
  public ModelAndView handleFeatureFlagAccessDenied(FeatureFlagAccessDeniedException ex) {
    var mav = new ModelAndView("error/feature-unavailable");
    mav.setStatus(HttpStatus.FORBIDDEN);
    mav.addObject("featureName", ex.featureName());
    return mav;
  }
}

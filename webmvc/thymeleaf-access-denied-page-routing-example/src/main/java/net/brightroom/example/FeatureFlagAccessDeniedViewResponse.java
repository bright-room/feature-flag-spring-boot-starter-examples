package net.brightroom.example;

import jakarta.servlet.http.HttpServletResponse;
import net.brightroom.featureflag.configuration.FeatureFlagAccessDeniedResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
class FeatureFlagAccessDeniedViewResponse implements FeatureFlagAccessDeniedResponse {

  @Override
  public void writeTo(HttpServletResponse response) {}

  @Override
  public ModelAndView toModelAndView() {
    return new ModelAndView("access-denied");
  }

  FeatureFlagAccessDeniedViewResponse() {}
}

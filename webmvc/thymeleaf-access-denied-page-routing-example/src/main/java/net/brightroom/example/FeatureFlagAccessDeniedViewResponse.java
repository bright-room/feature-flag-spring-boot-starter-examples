package net.brightroom.example;

import net.brightroom.featureflag.configuration.FeatureFlagAccessDeniedResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
class FeatureFlagAccessDeniedViewResponse implements FeatureFlagAccessDeniedResponse {

  @Override
  public ModelAndView toModelAndView() {
    return new ModelAndView("access-denied");
  }

  FeatureFlagAccessDeniedViewResponse() {}
}

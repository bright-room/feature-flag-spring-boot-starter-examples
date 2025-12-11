package net.brightroom.example;

import java.util.Map;
import net.brightroom.featureflag.configuration.FeatureFlagAccessDeniedResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.xml.JacksonXmlView;

@Component
public class CustomXmlResponse implements FeatureFlagAccessDeniedResponse {
  Integer statusCode = 405;
  Map<String, String> body = Map.of("error", "Method Not Allowed");

  @Override
  public ModelAndView toModelAndView() {
    JacksonXmlView view = new JacksonXmlView();
    view.setAttributesMap(this.body);

    ModelAndView modelAndView = new ModelAndView(view);
    modelAndView.setStatus(HttpStatusCode.valueOf(this.statusCode));

    return modelAndView;
  }

  CustomXmlResponse() {}
}

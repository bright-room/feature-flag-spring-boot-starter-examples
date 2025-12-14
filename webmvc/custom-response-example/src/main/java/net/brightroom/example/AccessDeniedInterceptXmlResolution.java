package net.brightroom.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;
import net.brightroom.featureflag.configuration.AccessDeniedInterceptResolution;
import org.springframework.stereotype.Component;
import tools.jackson.dataformat.xml.XmlMapper;

@Component
public class AccessDeniedInterceptXmlResolution implements AccessDeniedInterceptResolution {
  Integer statusCode = 405;
  Map<String, String> body = Map.of("error", "Method Not Allowed");

  @Override
  public void resolution(HttpServletRequest request, HttpServletResponse response) {
    response.setStatus(statusCode);
    response.setContentType("application/xml; charset=utf-8");

    XmlMapper xmlMapper = new XmlMapper();
    try (PrintWriter writer = response.getWriter()) {
      String xml = xmlMapper.writeValueAsString(body);
      writer.write(xml);
    } catch (Exception e) {
      throw new IllegalStateException("Response xml conversion failed", e);
    }
  }

  AccessDeniedInterceptXmlResolution() {}
}

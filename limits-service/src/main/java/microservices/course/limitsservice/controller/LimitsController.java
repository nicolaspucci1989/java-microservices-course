package microservices.course.limitsservice.controller;

import microservices.course.limitsservice.bean.Limits;
import microservices.course.limitsservice.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
  private final Configuration configuration;

  public LimitsController(Configuration configuration) {
    this.configuration = configuration;
  }

  @GetMapping("/limits")
  public Limits retrieveLimits() {
    return new Limits(configuration.getMinimum(), configuration.getMaximum());
  }
}

package microservicescourse.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(predicateSpec -> predicateSpec
            .path("/get")
            .filters(gatewayFilterSpec -> gatewayFilterSpec
                .addRequestHeader("MyHeader", "MyURI")
                .addRequestParameter("Param", "MyValue")
            )
            .uri("http://httpbin.org:80")
        )
        .route(predicateSpec -> predicateSpec
            .path("/currency-exchange/**")
            .uri("lb://currency-exchange")
        )
        .route(predicateSpec -> predicateSpec
            .path("/currency-conversion/**")
            .uri("lb://currency-conversion")
        )
        .route(predicateSpec -> predicateSpec
            .path("/currency-conversion-new/**")
            .filters(f -> f.rewritePath(
                "/currency-conversion-new/(?<segment>.*)",
                "/currency-conversion/${segment}"
            ))
            .uri("lb://currency-conversion")
        )
        .build();
  }
}

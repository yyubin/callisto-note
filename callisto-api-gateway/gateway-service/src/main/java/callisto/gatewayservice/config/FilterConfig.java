package callisto.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/go/**")
                        .filters(f -> f.addRequestHeader("admin-request", "admin-request-header")
                                .addResponseHeader("admin-response", "admin-response-header"))
                        .uri("http://localhost:3000/"))
                .build();

    }
}

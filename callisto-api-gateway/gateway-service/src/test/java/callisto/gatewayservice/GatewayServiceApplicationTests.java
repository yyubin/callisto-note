package callisto.gatewayservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testRouteToAdminServer1() {
        webTestClient.get().uri("/go/bar?name=yubin")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello yubin!");
    }

    @Test
    public void testRouteToAdminServer2() {
        webTestClient.get().uri("/go")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello World");
    }
    @Test
    void contextLoads() {
    }

}

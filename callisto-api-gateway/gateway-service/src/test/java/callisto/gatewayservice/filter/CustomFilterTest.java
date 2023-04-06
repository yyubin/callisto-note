package callisto.gatewayservice.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CustomFilterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testFilterConfig() {
        webTestClient.get().uri("/go").exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("admin-response", "admin-response-header");
    }

}
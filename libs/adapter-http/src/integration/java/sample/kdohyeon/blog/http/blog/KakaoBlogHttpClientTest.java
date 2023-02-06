package sample.kdohyeon.blog.http.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.MockRestServiceServer;
import sample.kdohyeon.blog.http.HttpIntegrationTest;

class KakaoBlogHttpClientTest extends HttpIntegrationTest {
    @Autowired
    KakaoBlogHttpClient kakaoBlogHttpClient;

    @Autowired
    MockRestServiceServer mockServer;

    @Test
    void test() {

    }
}

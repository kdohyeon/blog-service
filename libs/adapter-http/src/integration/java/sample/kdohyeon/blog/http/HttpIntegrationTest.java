package sample.kdohyeon.blog.http;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@RestClientTest(includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "sample\\.kdohyeon\\.blog\\.http\\..*")})
public abstract class HttpIntegrationTest {

}

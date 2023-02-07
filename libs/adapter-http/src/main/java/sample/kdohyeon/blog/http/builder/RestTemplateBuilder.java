package sample.kdohyeon.blog.http.builder;

import org.springframework.http.HttpHeaders;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.port.output.Clause;

public interface RestTemplateBuilder {

    String buildUri(Clause clause);

    HttpHeaders buildHeaders();

    boolean isTarget(RestApiType type);
}

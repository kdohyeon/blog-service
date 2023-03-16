package sample.kdohyeon.blog.http.blog;

import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.exception.InvalidRestApiRequestException;
import sample.kdohyeon.blog.http.builder.RestTemplateBuilder;
import sample.kdohyeon.blog.http.client.HttpClient;
import sample.kdohyeon.blog.port.output.Clause;

import java.util.List;

@Component
public class BlogRestTemplateClient {
    private final List<RestTemplateBuilder> restTemplateBuilders;
    private final HttpClient httpClient;

    public BlogRestTemplateClient(List<RestTemplateBuilder> restTemplateBuilders,
                                  HttpClient httpClient) {
        this.restTemplateBuilders = restTemplateBuilders;
        this.httpClient = httpClient;
    }

    public String query(Clause clause) {
        var restApiType = clause.getRestApiType();
        var restTemplateBuilder = restTemplateBuilders.stream()
                .filter(each -> each.isTarget(restApiType))
                .findAny()
                .orElseThrow(() -> new InvalidRestApiRequestException(restApiType));

        return httpClient.request(
                restTemplateBuilder.buildUri(clause),
                restApiType.getHttpMethod(),
                restTemplateBuilder.buildHeaders()
        );
    }
}

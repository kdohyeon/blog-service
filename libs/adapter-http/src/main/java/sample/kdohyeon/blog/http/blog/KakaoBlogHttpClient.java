package sample.kdohyeon.blog.http.blog;

import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.exception.InvalidRestApiRequestException;
import sample.kdohyeon.blog.http.blog.converter.BlogConverter;
import sample.kdohyeon.blog.http.blog.response.KakaoBlogResponse;
import sample.kdohyeon.blog.http.builder.RestTemplateBuilder;
import sample.kdohyeon.blog.http.client.HttpClient;
import sample.kdohyeon.blog.port.out.blog.BlogPort;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;
import sample.kdohyeon.blog.util.ObjectMapperUtil;

import java.util.List;

@Component
public class KakaoBlogHttpClient implements BlogPort {

    private final List<RestTemplateBuilder> restTemplateBuilders;
    private final BlogConverter blogConverter;
    private final HttpClient httpClient;
    private final ObjectMapperUtil objectMapperUtil;

    public KakaoBlogHttpClient(
            List<RestTemplateBuilder> restTemplateBuilders,
            BlogConverter blogConverter,
            HttpClient httpClient,
            ObjectMapperUtil objectMapperUtil
    ) {
        this.restTemplateBuilders = restTemplateBuilders;
        this.blogConverter = blogConverter;
        this.httpClient = httpClient;
        this.objectMapperUtil = objectMapperUtil;
    }

    @Override
    public Blog searchBlogDocuments(BlogSearchClause clause) {
        var restApiType = clause.getRestApiType();
        var restTemplateBuilder = restTemplateBuilders.stream()
                .filter(each -> each.isTarget(restApiType))
                .findAny()
                .orElseThrow(() -> new InvalidRestApiRequestException(restApiType));

        var response = httpClient.request(
                restTemplateBuilder.buildUri(clause),
                restApiType.getHttpMethod(),
                restTemplateBuilder.buildHeaders()
        );

        KakaoBlogResponse kakaoBlogResponse = objectMapperUtil.readValue(response, KakaoBlogResponse.class);
        return blogConverter.converter(kakaoBlogResponse, clause.getPage(), clause.getSize());
    }
}

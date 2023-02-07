package sample.kdohyeon.blog.http.builder;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.port.output.Clause;
import sample.kdohyeon.blog.port.output.blog.clause.NaverBlogSearchClause;

import java.util.Map;

@Component
public class NaverBlogSearchBuilder implements RestTemplateBuilder {

    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;

    private static final String QUERY = "query";
    private static final String SORT = "sort";
    private static final String PAGE = "start";
    private static final String SIZE = "display";

    private static final String RECENCY = "recency"; // date
    private static final String ACCURACY = "sim"; // 정확도순

    private static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    public NaverBlogSearchBuilder(
            @Value("${naver-open-api.base-url}") String baseUrl,
            @Value("${naver-open-api.auth.client-id}") String clientId,
            @Value("${naver-open-api.auth.client-secret}") String clientSecret
    ) {
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    private String buildSort(NaverBlogSearchClause clause) {
        if (clause.getSort() == BlogSearchQuerySort.RECENCY) {
            return RECENCY;
        }

        return ACCURACY;
    }

    @Override
    public String buildUri(Clause clause) {
        var searchClause = (NaverBlogSearchClause) clause;
        var restApiType = searchClause.getRestApiType();

        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(restApiType.getUri())
                .queryParam(QUERY, searchClause.getKeyword())
                .queryParam(SORT, buildSort(searchClause))
                .queryParam(PAGE, searchClause.getStart())
                .queryParam(SIZE, searchClause.getDisplay())
                .build()
                .toUriString();
    }

    @Override
    public HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put(X_NAVER_CLIENT_ID, clientId);
        headers.put(X_NAVER_CLIENT_SECRET, clientSecret);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return httpHeaders;
    }

    @Override
    public boolean isTarget(RestApiType type) {
        return type == RestApiType.NAVER_SEARCH_BLOGS;
    }
}

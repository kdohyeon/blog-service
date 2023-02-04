package sample.kdohyeon.blog.http.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.port.out.Clause;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

import java.util.Map;

@Component
public class KakaoBlogSearchBuilder implements RestTemplateBuilder {

    private final String baseUrl;
    private final String restApiKey;

    private static final String QUERY = "query";
    private static final String SORT = "sort";
    private static final String PAGE = "page";
    private static final String SIZE = "size";

    private static final String RECENCY = "recency";
    private static final String ACCURACY = "accuracy";

    private static final String AUTHORIZATION = "Authorization";

    public KakaoBlogSearchBuilder(
            @Value("${kakao-open-api.base-url}") String baseUrl,
            @Value("${kakao-open-api.auth.rest-api-key}") String restApiKey
    ) {
        this.baseUrl = baseUrl;
        this.restApiKey = restApiKey;
    }

    private String buildQuery(BlogSearchClause clause) {
        if (StringUtils.isNotBlank(clause.getUrl())) {
            return String.join(
                    StringUtils.SPACE,
                    Lists.newArrayList(clause.getUrl(), clause.getKeyword())
            );
        }

        return clause.getKeyword();
    }

    private String buildSort(BlogSearchClause clause) {
        if (clause.getSort() == BlogSearchQuerySort.RECENCY) {
            return RECENCY;
        }

        return ACCURACY;
    }

    @Override
    public String buildUri(Clause clause) {
        var searchClause = (BlogSearchClause) clause;
        var restApiType = searchClause.getRestApiType();

        var pageable = searchClause.getPageable();
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(restApiType.getUri())
                .queryParam(QUERY, buildQuery(searchClause))
                .queryParam(SORT, buildSort(searchClause))
                .queryParam(PAGE, pageable.getPageNumber())
                .queryParam(SIZE, pageable.getPageSize())
                .build()
                .toUriString();
    }

    @Override
    public HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put(AUTHORIZATION, "KakaoAK " + restApiKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return httpHeaders;
    }

    @Override
    public boolean isTarget(RestApiType type) {
        return type == RestApiType.KAKAO_SEARCH_BLOGS;
    }
}

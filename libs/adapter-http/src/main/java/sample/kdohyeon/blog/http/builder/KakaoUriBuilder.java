package sample.kdohyeon.blog.http.builder;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.http.contract.ServiceProvider;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

@Component
public class KakaoUriBuilder implements UriBuilder {

    private final String baseUrl;
    private final String searchBlogs;

    private static final String QUERY = "query";
    private static final String SORT = "sort";
    private static final String PAGE = "page";
    private static final String SIZE = "size";

    public KakaoUriBuilder(
            @Value("${kakao-open-api.base-url}") String baseUrl,
            @Value("${kakao-open-api.search-blogs}") String searchBlogs
    ) {
        this.baseUrl = baseUrl;
        this.searchBlogs = searchBlogs;
    }

    @Override
    public String buildBlogSearchUri(BlogSearchClause clause, Pageable pageable) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .path(searchBlogs)
                .queryParam(QUERY, buildQuery(clause))
                .queryParam(SORT, buildSort(clause))
                .queryParam(PAGE, pageable.getPageNumber())
                .queryParam(SIZE, pageable.getPageSize())
                .build()
                .toUriString();
    }

    @Override
    public boolean isTarget(ServiceProvider provider) {
        return provider == ServiceProvider.KAKAO;
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
            return "recency";
        }

        return "accuracy";
    }
}

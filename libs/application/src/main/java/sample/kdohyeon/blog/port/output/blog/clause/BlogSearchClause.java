package sample.kdohyeon.blog.port.output.blog.clause;

import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;

@Getter
public class BlogSearchClause {
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;
    private final RestApiType restApiType;

    // pagination
    private final int page;
    private final int size;

    @Builder
    public BlogSearchClause(String keyword, String url, BlogSearchQuerySort sort, RestApiType restApiType, int page, int size) {
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
        this.restApiType = restApiType;
        this.page = page;
        this.size = size;
    }
}

package sample.kdohyeon.blog.port.out.blog.clause;

import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;

@Getter
public class BlogSearchClause {
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;

    @Builder
    public BlogSearchClause(String keyword, String url, BlogSearchQuerySort sort) {
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
    }
}

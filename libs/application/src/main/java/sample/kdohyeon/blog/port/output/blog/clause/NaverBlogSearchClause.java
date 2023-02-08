package sample.kdohyeon.blog.port.output.blog.clause;

import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.port.output.Clause;

@Getter
public class NaverBlogSearchClause extends Clause {
    private final String keyword;
    private final BlogSearchQuerySort sort;

    private final int start;
    private final int display;

    @Builder
    public NaverBlogSearchClause(String keyword, BlogSearchQuerySort sort,
                                 int start, int display) {
        super(RestApiType.NAVER_SEARCH_BLOGS);
        this.keyword = keyword;
        this.sort = sort;
        this.start = start;
        this.display = display;
    }

    public static NaverBlogSearchClause of(BlogSearchClause clause) {
        return NaverBlogSearchClause.builder()
                .keyword(clause.getKeyword())
                .sort(clause.getSort())
                .start(clause.getPage())
                .display(clause.getSize())
                .build();
    }
}

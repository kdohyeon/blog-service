package sample.kdohyeon.blog.port.output.blog.clause;

import lombok.Builder;
import lombok.Getter;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.port.output.Clause;

@Getter
public class KakaoBlogSearchClause extends Clause {
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;

    private final int page;
    private final int size;

    @Builder
    public KakaoBlogSearchClause(String keyword, String url, BlogSearchQuerySort sort,
                                 int page, int size) {
        super(RestApiType.KAKAO_SEARCH_BLOGS);

        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    public static KakaoBlogSearchClause of(BlogSearchClause clause) {
        return KakaoBlogSearchClause.builder()
                .keyword(clause.getKeyword())
                .url(clause.getUrl())
                .sort(clause.getSort())
                .size(clause.getSize())
                .page(clause.getPage())
                .build();
    }
}

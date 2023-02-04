package sample.kdohyeon.blog.port.out.blog.clause;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.contract.blog.BlogSearchQuerySort;
import sample.kdohyeon.blog.port.out.Clause;

@Getter
public class BlogSearchClause implements Clause {
    private final String keyword;
    private final String url;
    private final BlogSearchQuerySort sort;

    private final RestApiType restApiType;

    private final Pageable pageable; // TODO: 그냥 변수 2개로 쪼개기 -> pageSize, pageNum, 굳이 pageable 객체를 사용할 필요는 없을 듯

    @Builder
    public BlogSearchClause(String keyword, String url, BlogSearchQuerySort sort,
                            RestApiType restApiType,
                            Pageable pageable) {
        this.keyword = keyword;
        this.url = url;
        this.sort = sort;
        this.restApiType = restApiType;
        this.pageable = pageable;
    }
}

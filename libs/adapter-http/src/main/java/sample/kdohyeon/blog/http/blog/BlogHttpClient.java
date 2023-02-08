package sample.kdohyeon.blog.http.blog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.port.output.blog.BlogPort;
import sample.kdohyeon.blog.port.output.blog.clause.BlogSearchClause;
import sample.kdohyeon.blog.port.output.blog.clause.KakaoBlogSearchClause;
import sample.kdohyeon.blog.port.output.blog.clause.NaverBlogSearchClause;

@Slf4j
@Component
public class BlogHttpClient implements BlogPort {

    private final KakaoBlogHttpClient kakaoBlogHttpClient;
    private final NaverBlogHttpClient naverBlogHttpClient;

    public BlogHttpClient(KakaoBlogHttpClient kakaoBlogHttpClient, NaverBlogHttpClient naverBlogHttpClient) {
        this.kakaoBlogHttpClient = kakaoBlogHttpClient;
        this.naverBlogHttpClient = naverBlogHttpClient;
    }

    @Override
    @CircuitBreaker(name = "BlogHttpClient", fallbackMethod = "callNaver")
    public Blog searchBlogDocuments(BlogSearchClause clause) {
        return callKakao(clause);
    }

    public Blog callKakao(BlogSearchClause clause) {
        return kakaoBlogHttpClient.searchBlogDocuments(KakaoBlogSearchClause.of(clause));
    }

    public Blog callNaver(BlogSearchClause clause, Throwable e) {
        log.warn("카카오 블로그 조회에 실패하여 네이버 블로그 조회로 변경합니다. errMsg: {}", e.getMessage(), e);
        return naverBlogHttpClient.searchBlogDocuments(NaverBlogSearchClause.of(clause));
    }
}

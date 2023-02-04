package sample.kdohyeon.blog.http.builder;

import org.springframework.data.domain.Pageable;
import sample.kdohyeon.blog.http.contract.ServiceProvider;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

public interface UriBuilder {
    String buildBlogSearchUri(BlogSearchClause clause, Pageable pageable);
    boolean isTarget(ServiceProvider provider);
}

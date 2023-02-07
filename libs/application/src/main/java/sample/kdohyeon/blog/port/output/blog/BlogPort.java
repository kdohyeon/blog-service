package sample.kdohyeon.blog.port.output.blog;

import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.port.output.blog.clause.BlogSearchClause;

public interface BlogPort {
    Blog searchBlogDocuments(BlogSearchClause clause);
}

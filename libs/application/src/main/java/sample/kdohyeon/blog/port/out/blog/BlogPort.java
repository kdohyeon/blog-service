package sample.kdohyeon.blog.port.out.blog;

import sample.kdohyeon.blog.domain.document.Blog;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

public interface BlogPort {
    Blog searchBlogs(BlogSearchClause clause);
}

package sample.kdohyeon.blog.port.out.blog;

import org.springframework.data.domain.Pageable;
import sample.kdohyeon.blog.domain.blog.Blog;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

public interface SearchBlogPort {
    Blog searchBlogs(BlogSearchClause clause, Pageable pageable);
}

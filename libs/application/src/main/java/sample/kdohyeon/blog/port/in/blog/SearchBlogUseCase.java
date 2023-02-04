package sample.kdohyeon.blog.port.in.blog;

import org.springframework.data.domain.Pageable;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;

public interface SearchBlogUseCase {
    BlogDto search(BlogSearchCommand command, Pageable pageable);
}

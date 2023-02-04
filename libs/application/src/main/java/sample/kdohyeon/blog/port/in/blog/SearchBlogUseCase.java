package sample.kdohyeon.blog.port.in.blog;

import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;

public interface SearchBlogUseCase {
    BlogDto search(BlogSearchCommand command);
}

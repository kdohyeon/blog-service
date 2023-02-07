package sample.kdohyeon.blog.port.input.blog;

import sample.kdohyeon.blog.port.input.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.input.blog.response.BlogDto;

public interface SearchBlogUseCase {
    BlogDto search(BlogSearchCommand command);
}

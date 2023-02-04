package sample.kdohyeon.blog.service.blog;

import org.springframework.stereotype.Service;
import sample.kdohyeon.blog.port.in.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;

@Service
public class BlogSearchService implements SearchBlogUseCase {

    @Override
    public BlogDto search(BlogSearchCommand command) {
        return null;
    }
}

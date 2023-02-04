package sample.kdohyeon.blog.service.blog;

import org.springframework.stereotype.Service;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.converter.blog.BlogDtoConverter;
import sample.kdohyeon.blog.port.in.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;
import sample.kdohyeon.blog.port.out.blog.SearchBlogPort;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

@Service
public class BlogSearchService implements SearchBlogUseCase {

    private final SearchBlogPort searchBlogPort;
    private final BlogDtoConverter blogDtoConverter;

    public BlogSearchService(SearchBlogPort searchBlogPort,
                             BlogDtoConverter blogDtoConverter) {
        this.searchBlogPort = searchBlogPort;
        this.blogDtoConverter = blogDtoConverter;
    }

    @Override
    public BlogDto search(BlogSearchCommand command) {
        var clause = BlogSearchClause.builder()
                .keyword(command.getKeyword())
                .sort(command.getSort())
                .url(command.getUrl())
                .pageable(command.getPageable())
                .restApiType(RestApiType.KAKAO_SEARCH_BLOGS)
                .build();
        var result = searchBlogPort.searchBlogs(clause);
        return blogDtoConverter.convert(result);
    }
}

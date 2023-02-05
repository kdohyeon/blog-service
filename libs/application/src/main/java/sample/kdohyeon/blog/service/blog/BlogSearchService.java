package sample.kdohyeon.blog.service.blog;

import org.springframework.stereotype.Service;
import sample.kdohyeon.blog.contract.RestApiType;
import sample.kdohyeon.blog.converter.blog.BlogDtoConverter;
import sample.kdohyeon.blog.port.in.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.in.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.in.blog.response.BlogDto;
import sample.kdohyeon.blog.port.in.statistics.UpsertBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.in.statistics.command.UpsertBlogStatisticsCommand;
import sample.kdohyeon.blog.port.out.blog.BlogPort;
import sample.kdohyeon.blog.port.out.blog.clause.BlogSearchClause;

@Service
public class BlogSearchService implements SearchBlogUseCase {

    private final BlogPort blogPort;
    private final UpsertBlogStatisticsUseCase upsertBlogStatisticsUseCase;
    private final BlogDtoConverter blogDtoConverter;

    public BlogSearchService(BlogPort blogPort,
                             UpsertBlogStatisticsUseCase upsertBlogStatisticsUseCase,
                             BlogDtoConverter blogDtoConverter) {
        this.blogPort = blogPort;
        this.upsertBlogStatisticsUseCase = upsertBlogStatisticsUseCase;
        this.blogDtoConverter = blogDtoConverter;
    }

    @Override
    public BlogDto search(BlogSearchCommand command) {
        // 검색어에 대한 통계 반영
        upsertBlogStatisticsUseCase.upsert(UpsertBlogStatisticsCommand.builder()
                .keyword(command.getKeyword())
                .build());

        // 검색어에 대한 조회
        var clause = BlogSearchClause.builder()
                .keyword(command.getKeyword())
                .sort(command.getSort())
                .url(command.getUrl())
                .pageable(command.getPageable())
                .restApiType(RestApiType.KAKAO_SEARCH_BLOGS)
                .build();
        var blog = blogPort.searchBlogDocuments(clause);

        return blogDtoConverter.convert(blog);
    }
}

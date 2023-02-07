package sample.kdohyeon.blog.service.blog;

import org.springframework.stereotype.Service;
import sample.kdohyeon.blog.converter.blog.BlogDtoConverter;
import sample.kdohyeon.blog.port.input.blog.SearchBlogUseCase;
import sample.kdohyeon.blog.port.input.blog.command.BlogSearchCommand;
import sample.kdohyeon.blog.port.input.blog.response.BlogDto;
import sample.kdohyeon.blog.port.input.statistics.UpsertBlogStatisticsUseCase;
import sample.kdohyeon.blog.port.input.statistics.command.UpsertBlogStatisticsCommand;
import sample.kdohyeon.blog.port.output.blog.BlogPort;
import sample.kdohyeon.blog.port.output.blog.clause.BlogSearchClause;
import sample.kdohyeon.blog.port.output.blog.clause.KakaoBlogSearchClause;

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
        upsertBlogStatisticsUseCase.increaseCountOrCreate(UpsertBlogStatisticsCommand.builder()
                .keyword(command.getKeyword())
                .build());

        // 검색어에 대한 조회
        var clause = BlogSearchClause.builder()
                .keyword(command.getKeyword())
                .url(command.getUrl())
                .sort(command.getSort())
                .page(command.getPage())
                .size(command.getSize())
                .build();
        var blog = blogPort.searchBlogDocuments(clause);

        // 객체 변환
        return blogDtoConverter.convert(blog);
    }
}
